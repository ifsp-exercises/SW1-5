document.addEventListener("DOMContentLoaded", start);

let bookForm;

async function start() {
  addFormEvents();
  addRemoveErrorsOnFocus();
}

function addFormEvents() {
  bookForm = document.querySelector(`[name=${elements.bookForm}]`);

  bookForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const inputs = Array.from(
      bookForm.querySelectorAll('input')
    );

    const formIsValid = validatebookForm(inputs);

    if (!formIsValid)
      return toastr.error('Please, fix the highlighted errors', 'Error');

    const bookData = inputs.reduce((accumulatedData, currentInput) => ({
      ...accumulatedData,
      [currentInput.name]: currentInput.value
    }), {});

    try {
      const response = await fetch(`${app_url}/save-book`, {
        method: 'POST',
        body: JSON.stringify(bookData),
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
