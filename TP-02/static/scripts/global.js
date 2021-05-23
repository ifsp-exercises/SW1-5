const app_url = 'http://localhost:8888/produto-manager';

/**
 * Elements names
 */
const elements = {
  editModal: 'edit-modal',
  cancelEditButton: 'cancel-edit-button',
  produtoForm: 'produto-form'
};

const SVGs = {
  edit: `<svg
    xmlns="http://www.w3.org/2000/svg"
    width="24"
    height="24"
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    stroke-width="2"
    stroke-linecap="round"
    stroke-linejoin="round"
    class="feather feather-edit"
  >
    <path
      d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"
    ></path>
    <path
      d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"
    ></path>
  </svg>`,
  trash: `<svg
    xmlns="http://www.w3.org/2000/svg"
    width="24"
    height="24"
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    stroke-width="2"
    stroke-linecap="round"
    stroke-linejoin="round"
    class="feather feather-trash-2"
  >
    <polyline points="3 6 5 6 21 6"></polyline>
    <path
      d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"
    ></path>
    <line x1="10" y1="11" x2="10" y2="17"></line>
    <line x1="14" y1="11" x2="14" y2="17"></line>
  </svg>`
}

function handleError(error) {
  console.warn('Error', error);
}

function validateEmail(email) {
  const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  return re.test(String(email).toLowerCase());
}

function addRemoveErrorsOnFocus() {
  Array.from(
    produtoForm.querySelectorAll('input')
  )
    .forEach(input => input.addEventListener('focus', (event) => {
      event.currentTarget.parentElement.dataset['error'] = false;
    }));
}

function validateProdutoForm(inputs) {
  let formIsValid = true;

  inputs.forEach((input) => {
    let invalid = false;

    switch (input.name) {
      case 'email':
        invalid = !validateEmail(input.value);
        break;

      default:
        invalid = !input.value?.trim();
        break;
    }

    input.parentElement.dataset['error'] = invalid;

    if (invalid)
      formIsValid = false;
  });

  return formIsValid;
}

