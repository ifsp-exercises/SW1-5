document.addEventListener("DOMContentLoaded", start);

let employeeForm;

async function start() {
  await loadCountries();

  addFormEvents();
}

function addFormEvents() {
  employeeForm = document.querySelector(`[name=${elements.employeeForm}]`);

  employeeForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const employeeData = Array.from(
      employeeForm.querySelectorAll('input')
    ).reduce((accumulatedData, currentInput) => ({
      ...accumulatedData,
      [currentInput.name]: currentInput.value
    }), {});

    employeeData['country'] = document
      .querySelector(`[name=${elements.selectCountry}]`).value;
  });
}

