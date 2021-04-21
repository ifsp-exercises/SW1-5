document.addEventListener("DOMContentLoaded", start);

let employeeForm;

async function start() {
  await loadCountries();

  addFormEvents();
}

function addFormEvents() {
  employeeForm = document.querySelector(`[name=${elements.employeeForm}]`);

  employeeForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const employeeData = Array.from(
      employeeForm.querySelectorAll('input')
    ).reduce((accumulatedData, currentInput) => ({
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
