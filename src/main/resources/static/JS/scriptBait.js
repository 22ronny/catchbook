function loadBaits(fishTypeId, dropdownId) {
    fetch(`http://localhost:8080/api/getAllBaitsForFishType/${fishTypeId}`)
    .then(response => response.json())
    .then(data => {
        const dropdown = document.getElementById(dropdownId);
        dropdown.innerHTML = ''; // Leeren Sie die Dropdown-Liste
 
        data.forEach(bait => {
            const option = document.createElement('option');
            option.value = bait.id;
            option.text = bait.bait;
            dropdown.add(option);
        });
    })
    .catch(error => console.error('Fehler beim Laden der Köder:', error));
 }
 
 function updateDropdowns(fishTypeId) {
    loadBaits(fishTypeId, 'deleteBait');
    loadBaits(fishTypeId, 'updateBait');
 }
 
 window.onload = function() {
    const deleteFishType = document.getElementById('deleteFishType');
    currentFishTypeId = deleteFishType.value;
    loadBaits(currentFishTypeId, 'deleteBait');
    loadBaits(currentFishTypeId, 'updateBait');
 
    deleteFishType.addEventListener('change', function() {
        currentFishTypeId = this.value;
        loadBaits(currentFishTypeId, 'deleteBait');
        loadBaits(currentFishTypeId, 'updateBait');
    });
 };
 
 function addBait() {
    const fishTypeId = document.getElementById('fishTypeAdd').value;
    const newBait = document.getElementById('newBait').value;
 
    fetch(`http://localhost:8080/api/addBait`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            fishType: { id: parseInt(fishTypeId) },
            bait: newBait
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Köder hinzugefügt:', data);
        updateDropdowns(fishTypeId);
    })
    .catch(error => console.error('Fehler beim Hinzufügen des Köders:', error));
 }
 
 function deleteBait() {
    const fishTypeId = document.getElementById('deleteFishType').value;
    const baitId = document.getElementById('deleteBait').value;
 
    fetch(`http://localhost:8080/api/deleteBait/${baitId}`, {
        method: 'DELETE',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Köder gelöscht:', data);
        updateDropdowns(fishTypeId);
    })
    .catch(error => console.error('Fehler beim Löschen des Köders:', error));
 }
 
 function updateBait() {
    const fishTypeId = document.getElementById('updateFishType').value;
    const baitId = document.getElementById('updateBait').value;
    const updatedBait = document.getElementById('updatedBait').value;
 
    fetch(`http://localhost:8080/api/patchBait/${baitId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ bait: updatedBait })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Köder aktualisiert:', data);
        updateDropdowns(fishTypeId);
    })
    .catch(error => console.error('Fehler beim Aktualisieren des Köders:', error));
 }