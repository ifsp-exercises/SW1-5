document.addEventListener("DOMContentLoaded", start);

let editModal;
let produtos = [];
let produtoList = null;
let currentProdutoID = null;

async function start() {
  produtoList = document.querySelector('.produto-list');
  editModal = document.querySelector(`[name=${elements.editModal}]`)
  produtoForm = document.querySelector(`[name=${elements.produtoForm}]`);

  produtoForm.addEventListener('submit', handleFormSubmit);

  loadProdutos().then(() => {
    addActionsEvents();
    fillProdutoList();
  });

  addRemoveErrorsOnFocus();
}

async function loadProdutos() {
  produtoList.style.display = 'none';

  const response = await fetch(`${app_url}/get-produtos`, {
    method: 'GET',
  });

  const data = await response.json();

  const { data: fetchedProdutos } = data;

  produtos = fetchedProdutos;
}

function addActionsEvents() {
  document.querySelector(`[name=${elements.cancelEditButton}]`)
    .addEventListener('click', () => {
      currentProdutoID = null;
      editModal.style.display = 'none';
    });
}

function handleOpenEditModal(produtoId) {
  const produto = produtos.find(emp => emp.id === produtoId);

  if (!produto || produto.length) return toastr.error('Produto not found.', 'Error');

  currentProdutoID = produto.id;

  Array.from(
    produtoForm.querySelectorAll('input')
  ).forEach((input) => {
    input.value = produto[input.name];
  });

  editModal.style.display = 'flex';
}

async function handleDelete(produtoId) {
  const produto = produtos.find(emp => emp.id === produtoId);

  if (!produto || produto.length) return toastr.error('Produto not found.', 'Error');

  const confirmDelete = confirm(`Are you sure to delete the produto "${produto.name}"?`);

  if (!confirmDelete) return;

  try {
    const response = await fetch(`${app_url}/delete-produto?id=${produto.id}`, {
      method: 'DELETE',
    });

    const data = await response.json();

    if (!data.success) throw new Error(data.message);

    toastr.success(data.message, 'Success');

    await loadProdutos();
    fillProdutoList();
  } catch (error) {
    handleError(error);

    toastr.error(error.message, 'Error');
  }
}

function fillProdutoList() {
  const produtoListBody = document.querySelector('.produto-list > tbody');

  produtoListBody.innerHTML = '';

  produtos.forEach(produto => {
    const tr = document.createElement('tr');

    Object.keys(produto).forEach(property => {
      const td = document.createElement('td');

      td.innerText = produto[property];

      tr.appendChild(td);
    });

    const editButton = document.createElement('button');
    editButton.innerHTML = SVGs.edit;
    editButton.addEventListener('click', () => handleOpenEditModal(produto.id));

    const removeButton = document.createElement('button');
    removeButton.innerHTML = SVGs.trash;
    removeButton.addEventListener('click', () => handleDelete(produto.id));

    const actionsTd = document.createElement('td');
    actionsTd.classList.add('produto-actions');

    actionsTd.appendChild(editButton);
    actionsTd.appendChild(removeButton);

    tr.appendChild(actionsTd);

    produtoListBody.appendChild(tr);
  })

  if (produtos.length) produtoList.style.display = '';
}

async function handleFormSubmit(event) {
  event.preventDefault();

  const inputs = Array.from(
    produtoForm.querySelectorAll('input')
  );

  const formIsValid = validateProdutoForm(inputs);

  if (!formIsValid)
    return toastr.error('Please, fix the highlighted errors', 'Error');

  const produtoData = Array.from(
    event.currentTarget.querySelectorAll('input')
  ).reduce((accumulatedData, currentInput) => ({
    ...accumulatedData,
    [currentInput.name]: currentInput.value
  }), {});

  produtoData.id = currentProdutoID;

  try {
    const response = await fetch(`${app_url}/update-produto`, {
      method: 'PUT',
      body: JSON.stringify(produtoData),
    });

    const data = await response.json();

    if (!data.success) throw data;

    toastr.success(data.message, 'Success');

    await loadProdutos();
    fillProdutoList();

    currentProdutoID = null;
    editModal.style.display = 'none';
  } catch (error) {
    handleError(error);

    toastr.error(error.message, 'Error');
  }
}
