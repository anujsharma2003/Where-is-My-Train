// API Configuration
const API_BASE_URL = 'http://localhost:8080';

// DOM Elements
const sourceCodeInput = document.getElementById('sourceCode');
const destinationCodeInput = document.getElementById('destinationCode');
const trainsContainer = document.getElementById('trainsContainer');
const loading = document.getElementById('loading');
const routeInfo = document.getElementById('routeInfo');
const resultsHeader = document.getElementById('resultsHeader');
const noResults = document.getElementById('noResults');
const errorMessage = document.getElementById('errorMessage');
const errorText = document.getElementById('errorText');
const trainCount = document.getElementById('trainCount');

// Route information elements
const fromCode = document.getElementById('fromCode');
const fromName = document.getElementById('fromName');
const toCode = document.getElementById('toCode');
const toName = document.getElementById('toName');

// Swap station codes
function swapStations() {
    const temp = sourceCodeInput.value;
    sourceCodeInput.value = destinationCodeInput.value;
    destinationCodeInput.value = temp;
}

// Calculate journey duration
function calculateDuration(departureTime, arrivalTime) {
    const [depHours, depMinutes] = departureTime.split(':').map(Number);
    const [arrHours, arrMinutes] = arrivalTime.split(':').map(Number);
    
    let totalMinutes = (arrHours * 60 + arrMinutes) - (depHours * 60 + depMinutes);
    
    // Handle overnight journeys
    if (totalMinutes < 0) {
        totalMinutes += 24 * 60;
    }
    
    const hours = Math.floor(totalMinutes / 60);
    const minutes = totalMinutes % 60;
    
    return `${hours}h ${minutes}m`;
}

// Format time for display
function formatTime(time) {
    const [hours, minutes] = time.split(':');
    const hour = parseInt(hours);
    const ampm = hour >= 12 ? 'PM' : 'AM';
    const formattedHour = hour % 12 || 12;
    return `${formattedHour}:${minutes} ${ampm}`;
}

// Hide all messages and sections
function hideAllMessages() {
    loading.style.display = 'none';
    routeInfo.style.display = 'none';
    resultsHeader.style.display = 'none';
    noResults.style.display = 'none';
    errorMessage.style.display = 'none';
    trainsContainer.innerHTML = '';
}

// Show error message
function showError(message) {
    errorText.textContent = message;
    errorMessage.style.display = 'flex';
    setTimeout(() => {
        errorMessage.style.display = 'none';
    }, 5000);
}

// Update route information
function updateRouteInfo(sourceStation, destinationStation) {
    fromCode.textContent = sourceStation.stationCode;
    fromName.textContent = sourceStation.stationName;
    toCode.textContent = destinationStation.stationCode;
    toName.textContent = destinationStation.stationName;
    routeInfo.style.display = 'block';
}

// Render train card
function renderTrainCard(train) {
    const duration = calculateDuration(train.departureTime, train.arrivalTime);
    const formattedDepartureTime = formatTime(train.departureTime);
    const formattedArrivalTime = formatTime(train.arrivalTime);
    
    const trainCard = document.createElement('div');
    trainCard.className = 'train-card';
    trainCard.innerHTML = `
        <div class="train-header">
            <div class="train-info">
                <div class="train-icon-box">🚂</div>
                <div>
                    <div class="train-name">${train.train.trainName}</div>
                    <div class="train-number">Train #${train.train.trainNumber}</div>
                </div>
            </div>
            <div class="train-badge">Available</div>
        </div>
        
        <div class="train-schedule">
            <div class="time-box">
                <div class="time">${formattedDepartureTime}</div>
                <div class="label">Departure</div>
            </div>
            
            <div style="position: relative;">
                <div class="journey-line">
                    <div class="duration-badge">${duration}</div>
                </div>
            </div>
            
            <div class="time-box">
                <div class="time">${formattedArrivalTime}</div>
                <div class="label">Arrival</div>
            </div>
        </div>
        
        <div class="train-footer">
            <div class="stations-info">
                <div class="station-detail">
                    <div class="station-code-small">${train.sourceId.stationCode}</div>
                    <div class="station-name-small">${train.sourceId.stationName}</div>
                </div>
                <div style="display: flex; align-items: center; color: #667eea; font-size: 1.5em;">→</div>
                <div class="station-detail">
                    <div class="station-code-small">${train.destinationId.stationCode}</div>
                    <div class="station-name-small">${train.destinationId.stationName}</div>
                </div>
            </div>
            <button class="book-btn" onclick="bookTrain(${train.id}, '${train.train.trainName}', '${train.train.trainNumber}')">
                Book Now
            </button>
        </div>
    `;
    
    return trainCard;
}

// Book train function
function bookTrain(trainId, trainName, trainNumber) {
    alert(`Booking Details:\n\nTrain: ${trainName}\nTrain Number: ${trainNumber}\nTrain ID: ${trainId}\n\nProceeding to payment...`);
}

// Search trains
async function searchTrains() {
    const sourceCode = sourceCodeInput.value.trim().toUpperCase();
    const destinationCode = destinationCodeInput.value.trim().toUpperCase();
    
    // Validation
    if (!sourceCode || !destinationCode) {
        showError('Please enter both source and destination station codes');
        return;
    }
    
    if (sourceCode === destinationCode) {
        showError('Source and destination cannot be the same');
        return;
    }
    
    // Reset UI
    hideAllMessages();
    loading.style.display = 'block';
    
    try {
        // Make API call
        const url = `${API_BASE_URL}/search/byCode?sourceCode=${sourceCode}&destinationCode=${destinationCode}`;
        console.log('Fetching from:', url);
        
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
            mode: 'cors'
        });
        
        console.log('Response status:', response.status);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const trains = await response.json();
        console.log('Trains received:', trains);
        
        // Hide loading
        loading.style.display = 'none';
        
        // Check if trains found
        if (!trains || trains.length === 0) {
            noResults.style.display = 'block';
            return;
        }
        
        // Update route information
        updateRouteInfo(trains[0].sourceId, trains[0].destinationId);
        
        // Show results header
        resultsHeader.style.display = 'flex';
        trainCount.textContent = trains.length;
        
        // Render train cards
        trains.forEach((train, index) => {
            setTimeout(() => {
                const card = renderTrainCard(train);
                trainsContainer.appendChild(card);
            }, index * 100);
        });
        
    } catch (error) {
        loading.style.display = 'none';
        console.error('Error details:', error);
        
        if (error.name === 'TypeError' && error.message.includes('Failed to fetch')) {
            showError('Cannot connect to server. Make sure the backend is running on http://localhost:8080 and CORS is enabled.');
        } else {
            showError(`Error: ${error.message}`);
        }
    }
}

// Add enter key support for search inputs
sourceCodeInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        searchTrains();
    }
});

destinationCodeInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        searchTrains();
    }
});

// Test connection on page load
window.addEventListener('load', () => {
    console.log('Page loaded. Backend URL:', API_BASE_URL);
});