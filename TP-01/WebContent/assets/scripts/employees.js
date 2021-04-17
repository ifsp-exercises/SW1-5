document.addEventListener("DOMContentLoaded", start);

let editModal;

async function start() {
  editModal = document.querySelector(`[name=${elements.editModal}]`)

  document.querySelector(`[name=${elements.cancelEditButton}]`)
    .addEventListener('click', () => {
      editModal.style.display = 'none';
    });

  await loadCountries();
}

function handleEdit(employeeId) {
  console.log('Editing employee id ' + employeeId)

  editModal.style.display = 'flex';
}