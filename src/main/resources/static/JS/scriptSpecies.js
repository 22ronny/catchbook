// Funktion zum Abrufen aller Fischarten für einen bestimmten Fish Type
function getSpeciesForFishType() {
    var fishType = document.getElementById("deleteFishType").value;

    var speciesListDropdown = document.getElementById("speciesList");
    speciesListDropdown.innerHTML = "";

    fetch(`http://localhost:8080/api/getAllSpeciesForFishType/${fishType}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(species => {
                speciesListDropdown.add(new Option(species.name, species.id));
            });
        })
        .catch(error => console.error('Error:', error));
}

// Funktion zum Hinzufügen einer neuen Fischart
function addNewSpecies() {
    var fishTypeId = document.getElementById("fishType").value;
    var newSpeciesName = document.getElementById("newSpeciesName").value;

    fetch('http://localhost:8080/api/addSpecies', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            fishType: {
                id: fishTypeId,
            },
            name: newSpeciesName,
        }),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        getSpeciesForFishType(); // Aktualisiere die Fischartenliste nach dem Hinzufügen
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// Funktion zum Löschen einer Fischart
function deleteSpecies() {
    var speciesId = document.getElementById("speciesList").value;

    fetch(`http://localhost:8080/api/deleteSpecies/${speciesId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
            console.log('Success: Fischart gelöscht');
            getSpeciesForFishType(); // Aktualisiere die Fischartenliste nach dem Löschen
        } else {
            console.error('Fehler beim Löschen der Fischart');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// Funktion zum Abrufen aller Fischarten für einen bestimmten Fish Type (Ändern)
function getSpeciesForFishTypeEdit() {
    var fishType = document.getElementById("editFishType").value;

    var speciesListDropdown = document.getElementById("editSpeciesList");
    speciesListDropdown.innerHTML = "";

    fetch(`http://localhost:8080/api/getAllSpeciesForFishType/${fishType}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(species => {
                speciesListDropdown.add(new Option(species.name, species.id));
            });
        })
        .catch(error => console.error('Error:', error));
}

// Funktion zum Befüllen des Textfelds beim Ändern
function populateEditForm() {
    var selectedSpeciesId = document.getElementById("editSpeciesList").value;
    var selectedSpeciesName = document.getElementById("editSpeciesList").options[document.getElementById("editSpeciesList").selectedIndex].text;

    document.getElementById("editSpeciesName").value = selectedSpeciesName;
}

// Funktion zum Ändern einer Fischart
function editSpecies() {
    var speciesId = document.getElementById("editSpeciesList").value;
    var newSpeciesName = document.getElementById("editSpeciesName").value;

    fetch(`http://localhost:8080/api/patchSpecies/${speciesId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: newSpeciesName,
        }),
    })
    .then(response => {
        if (response.ok) {
            console.log('Success: Fischart geändert');
            getSpeciesForFishTypeEdit(); // Aktualisiere die Fischartenliste nach dem Ändern
        } else {
            console.error('Fehler beim Ändern der Fischart');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// Lade die Fischarten für den ausgewählten Fish Type beim Seitenstart
document.addEventListener("DOMContentLoaded", function() {
    getSpeciesForFishType();
    getSpeciesForFishTypeEdit();
});