let places;

// Funktion zum Aktualisieren des Dropdown-Menüs
function updateDropdownMenu(places) {
    const placesDropdown = document.getElementById("placesDropdown");
    const placesSelect = document.getElementById("places");

    placesDropdown.innerHTML = "";
    placesSelect.innerHTML = "";

    places.forEach(place => {
        const option = document.createElement("option");
        option.value = place.id;
        option.text = place.place;
        placesDropdown.add(option);

        const optionSelect = document.createElement("option");
        optionSelect.value = place.id;
        optionSelect.text = place.place;
        placesSelect.add(optionSelect);
    });
}

// Funktion zum Laden der Platzdaten und Aktualisieren aller Dropdown-Menüs
function loadAndRefreshDropdowns() {
    fetch('http://localhost:8080/api/getAll')
        .then(response => response.json())
        .then(data => {
            places = data;
            updateDropdownMenu(places);
        })
        .catch(error => {
            console.error('Fehler beim Laden der Platzdaten:', error.message);
            alert('Fehler beim Laden der Platzdaten.');
        });
}

// Funktion zum Hinzufügen eines Ortes
function addPlace() {
    var placeName = document.getElementById('placeInput').value;

    if (placeName.trim() === "") {
        alert("Bitte einen Platznamen eingeben.");
        return;
    }

    var data = {
        place: placeName
    };

    fetch('http://localhost:8080/api/addPlace', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.text())
        .then(result => {
            console.log('Serverantwort:', result);

            if (result.startsWith('Platz')) {
                alert(result);
            } else {
                alert('Erfolgreich hinzugefügt!');
                loadAndRefreshDropdowns();
            }
        })
        .catch(error => {
            console.error('Fehler beim Hinzufügen des Platzes:', error);
            alert('Es ist ein Fehler aufgetreten: ' + error.message);
        });
}

// Funktion zum Löschen des ausgewählten Ortes
function deleteSelectedPlace() {
    var selectedPlaceId = $('#placesDropdown').val();

    if (selectedPlaceId) {
        $.ajax({
            url: 'http://localhost:8080/api/deletePlace/' + selectedPlaceId,
            type: 'DELETE',
            success: function (response) {
                if (response.message) {
                    alert(response.message);
                } else {
                    loadAndRefreshDropdowns();
                }
            },
            error: function (error) {
                alert(error.responseText);
            }
        });
    } else {
        alert('Bitte einen Platz auswählen, um ihn zu löschen.');
    }
}

// Funktion zum Aktualisieren des Textfelds basierend auf der Dropdown-Auswahl
function updateSelectedPlace() {
    const placesDropdown = document.getElementById("places");
    const selectedId = placesDropdown.value;
    const selectedPlace = places.find(place => place.id == selectedId);
    const selectedPlaceInput = document.getElementById("selectedPlace");

    selectedPlaceInput.value = selectedPlace ? selectedPlace.place : "";
}

// Funktion zum Senden des PATCH-Anforderung an die REST-API
function changePlace() {
    const placesDropdown = document.getElementById("places");
    const selectedId = placesDropdown.value;
    const selectedPlace = places.find(place => place.id == selectedId);
    const selectedPlaceInput = document.getElementById("selectedPlace");

    if (selectedPlace) {
        const apiUrl = `http://localhost:8080/api/patchPlace/${selectedPlace.id}`;
        const requestBody = { "place": selectedPlaceInput.value };

        fetch(apiUrl, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Fehler beim Ändern des Platzes. Status: ' + response.status);
            }
            alert('Platz erfolgreich geändert!');
            loadAndRefreshDropdowns();
        })
        .catch(error => {
            console.error('Fehler beim Ändern des Platzes:', error.message);
            alert('Fehler beim Ändern des Platzes.');
        });
    }
}

// ... (andere Funktionen für das Ändern von Orten)

// Initialisiere das Dropdown-Menü beim Laden der Seite
$(document).ready(function () {
    loadAndRefreshDropdowns();
});

// Event-Listener für Dropdown-Änderungen
document.getElementById("places").addEventListener("change", updateSelectedPlace);
