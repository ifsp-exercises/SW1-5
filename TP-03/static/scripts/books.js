document.addEventListener("DOMContentLoaded", start);

let editModal;
let books = [];
let bookList = null;
let currentbookID = null;

async function start() {
  bookList = document.querySelector('.book-list');
  editModal = document.querySelector(`[name=${elements.editModal}]`)
  bookForm = document.querySelector(`[name=${elements.bookForm}]`);

  bookForm.addEventListener('submit', handleFormSubmit);

  loadbooks().then(() => {
    addActionsEvents();
    fillbookList();
  });

  addRemoveErrorsOnFocus();
}

async function loadbooks() {
  bookList.style.display = 'none';

  const response = await fetch(`${app_url}/get-books`, {
    method: 'GET',
  });

  const data = await response.json();

  const { data: fetchedbooks } = data;

  books = fetchedbooks;
}

function addActionsEvents() {
  document.querySelector(`[name=${elements.cancelEditButton}]`)
    .addEventListener('click', () => {
      currentbookID = null;
      editModal.style.display = 'none';
    });
}

function handleOpenEditModal(bookId) {
  const book = books.find(emp => emp.id === bookId);

  if (!book || book.length) return toastr.error('book not found.', 'Error');

  currentbookID = book.id;

  Array.from(
    bookForm.querySelectorAll('input')
  ).forEach((input) => {
    input.value = book[input.name];
  });

  editModal.style.display = 'flex';
}

async function handleDelete(bookId) {
  const book = books.find(emp => emp.id === bookId);

  if (!book || book.length) return toastr.error('book not found.', 'Error');

  const confirmDelete = confirm(`Are you sure to delete the book "${book.name}"?`);

  if (!confirmDelete) return;

  try {
    const response = await fetch(`${app_url}/delete-book?id=${book.id}`, {
      method: 'DELETE',
    });

    const data = await response.json();

    if (!data.success) throw new Error(data.message);

    toastr.success(data.message, 'Success');

    await loadbooks();
    fillbookList();
  } catch (error) {
    handleError(error);

    toastr.error(error.message, 'Error');
  }
}

function fillbookList() {
  const bookListBody = document.querySelector('.book-list > tbody');

  bookListBody.innerHTML = '';

  books.forEach(book => {
    const tr = document.createElement('tr');

    Object.keys(book).forEach(property => {
      const td = document.createElement('td');

      td.innerText = book[property];

      tr.appendChild(td);
    });

    const editButton = document.createElement('button');
    editButton.innerHTML = SVGs.edit;
    editButton.addEventListener('click', () => handleOpenEditModal(book.id));

    const removeButton = document.createElement('button');
    removeButton.innerHTML = SVGs.trash;
    removeButton.addEventListener('click', () => handleDelete(book.id));

    const actionsTd = document.createElement('td');
    actionsTd.classList.add('book-actions');

    actionsTd.appendChild(editButton);
    actionsTd.appendChild(removeButton);

    tr.appendChild(actionsTd);

    bookListBody.appendChild(tr);
  })

  if (books.length) bookList.style.display = '';
}

async function handleFormSubmit(event) {
  event.preventDefault();

  const inputs = Array.from(
    bookForm.querySelectorAll('input')
  );

  const formIsValid = validatebookForm(inputs);

  if (!formIsValid)
    return toastr.error('Please, fix the highlighted errors', 'Error');

  const bookData = Array.from(
    event.currentTarget.querySelectorAll('input')
  ).reduce((accumulatedData, currentInput) => ({
    ...accumulatedData,
    [currentInput.name]: currentInput.value
  }), {});

  bookData.id = currentbookID;

  try {
    const response = await fetch(`${app_url}/update-book`, {
      method: 'PUT',
      body: JSON.stringify(bookData),
    });

    const data = await response.json();

    if (!data.success) throw data;

    toastr.success(data.message, 'Success');

    await loadbooks();
    fillbookList();

    currentbookID = null;
    editModal.style.display = 'none';
  } catch (error) {
    handleError(error);

    toastr.error(error.message, 'Error');
  }
}
