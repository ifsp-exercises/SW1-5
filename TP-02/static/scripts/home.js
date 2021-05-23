document.addEventListener("DOMContentLoaded", start);

let produtoForm;

async function start() {
  addFormEvents();
  addRemoveErrorsOnFocus();
}

function addFormEvents() {
  produtoForm = document.querySelector(`[name=${elements.produtoForm}]`);

  produtoForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const inputs = Array.from(
      produtoForm.querySelectorAll('input')
    );

    const formIsValid = validateProdutoForm(inputs);

    if (!formIsValid)
      return toastr.error('Please, fix the highlighted errors', 'Error');

    const produtoData = inputs.reduce((accumulatedData, currentInput) => ({
      ...accumulatedData,
      [currentInput.name]: currentInput.value
    }), {});

    try {
      const response = await fetch(`${app_url}/save-produto`, {
        method: 'POST',
        body: JSON.stringify(produtoData),
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
