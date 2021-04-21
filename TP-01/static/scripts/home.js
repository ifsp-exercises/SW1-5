document.addEventListener("DOMContentLoaded", start);

let employeeForm;

async function start() {
  loadCountries();
  addFormEvents();
  addRemoveErrorsOnFocus();
}

function addFormEvents() {
  employeeForm = document.querySelector(`[name=${elements.employeeForm}]`);

  employeeForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const inputs = Array.from(
      employeeForm.querySelectorAll('input')
    );

    const formIsValid = validateEmployeeForm(inputs);

    if (!formIsValid)
      return toastr.error('Please, fix the highlighted errors', 'Error');

    const employeeData = inputs.reduce((accumulatedData, currentInput) => ({
      ...accumulatedData,
      [currentInput.name]: currentInput.value
    }), {});

    employeeData['country'] = document
      .querySelector(`[name=${elements.selectCountry}]`).value;

    try {
      const response = await fetch(`${app_url}/save-employee`, {
        method: 'POST',
        body: JSON.stringify(employeeData),
      });

      const data = await response.json();

      if (!data.success) throw data;

      toastr.success(data.message, 'Success');

      document.querySelector('button[type=reset]')?.click();
    } catch (error) {
      handleError(error);

      toastr.error(error.message, 'Error');
    }
  });
}
