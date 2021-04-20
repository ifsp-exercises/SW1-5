document.addEventListener("DOMContentLoaded", start);

let editModal;
let employees = [];
let employeeList = null;
let currentEmployeeID = null;

async function start() {
  employeeList = document.querySelector('.employee-list');
  editModal = document.querySelector(`[name=${elements.editModal}]`)
  employeeForm = document.querySelector(`[name=${elements.employeeForm}]`);

  employeeForm.addEventListener('submit', handleFormSubmit);

  await loadEmployees();

  addActionsEvents();

  await loadCountries();

  fillEmployeeList();
}

async function loadEmployees() {
  employeeList.style.display = 'none';

  const response = await fetch(`${app_url}/get-employees`, {
    method: 'GET',
  });

  const data = await response.json();

  const { data: fetchedEmployees } = data;

  employees = fetchedEmployees;
}

function addActionsEvents() {
  document.querySelector(`[name=${elements.cancelEditButton}]`)
    .addEventListener('click', () => {
      currentEmployeeID = null;
      editModal.style.display = 'none';
    });
}

function handleOpenEditModal(employeeId) {
  const employee = employees.find(emp => emp.id === employeeId);

  if (!employee || employee.length) return toastr.error('Employee not found.', 'Error');

  currentEmployeeID = employee.id;

  Array.from(
    employeeForm.querySelectorAll('input')
  ).forEach((input) => {
    input.value = employee[input.name];
  });

  document
    .querySelector(`[name=${elements.selectCountry}]`).value = employee.country;

  editModal.style.display = 'flex';
}

async function handleDelete(employeeId) {
  const employee = employees.find(emp => emp.id === employeeId);

  if (!employee || employee.length) return toastr.error('Employee not found.', 'Error');

  const confirmDelete = confirm(`Are you sure to delete the employee "${employee.name}"?`);

  if (!confirmDelete) return;

  try {
    const response = await fetch(`${app_url}/delete-employee?id=${employee.id}`, {
      method: 'DELETE',
    });

    const data = await response.json();

    if (!data.success) throw new Error(data.message);

    toastr.success(data.message, 'Success');

    await loadEmployees();
    fillEmployeeList();
  } catch (error) {
    handleError(error);

    toastr.error(error.message, 'Error');
  }
}

function fillEmployeeList() {
  const employeeListBody = document.querySelector('.employee-list > tbody');

  employeeListBody.innerHTML = '';

  employees.forEach(employee => {
    const tr = document.createElement('tr');

    Object.keys(employee).forEach(property => {
      const td = document.createElement('td');

      td.innerText = employee[property];

      tr.appendChild(td);
    });

    const editButton = document.createElement('button');
    editButton.innerHTML = SVGs.edit;
    editButton.addEventListener('click', () => handleOpenEditModal(employee.id));

    const removeButton = document.createElement('button');
    removeButton.innerHTML = SVGs.trash;
    removeButton.addEventListener('click', () => handleDelete(employee.id));

    const actionsTd = document.createElement('td');
    actionsTd.classList.add('employee-actions');

    actionsTd.appendChild(editButton);
    actionsTd.appendChild(removeButton);

    tr.appendChild(actionsTd);

    employeeListBody.appendChild(tr);
  })

  if (employees.length) employeeList.style.display = '';
}

async function handleFormSubmit(event) {
  event.preventDefault();

  const employeeData = Array.from(
    event.currentTarget.querySelectorAll('input')
  ).reduce((accumulatedData, currentInput) => ({
    ...accumulatedData,
    [currentInput.name]: currentInput.value
  }), {});

  employeeData.id = currentEmployeeID;

  employeeData.country = document
    .querySelector(`[name=${elements.selectCountry}]`).value;

  try {
    const response = await fetch(`${app_url}/update-employee`, {
      method: 'PUT',
      body: JSON.stringify(employeeData),
    });

    const data = await response.json();

    if (!data.success) throw data;

    toastr.success(data.message, 'Success');

    await loadEmployees();
    fillEmployeeList();

    currentEmployeeID = null;
    editModal.style.display = 'none';
  } catch (error) {
    handleError(error);

    toastr.error(error.message, 'Error');
  }

  console.log(employeeData)
}
