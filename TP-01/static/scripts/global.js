/**
 * Elements names
 */
const elements = {
  selectCountry: 'country',
  editModal: 'edit-modal',
  cancelEditButton: 'cancel-edit-button',
  employeeForm: 'employee-form'
};

/**
 * LocalStorage keys used by the app
 */
const localStorageKeys = {
  countries: 'SWI_5_TP_01_COUNTRIES'
}

async function loadCountries() {
  const storedCountries = JSON.parse(localStorage.getItem(localStorageKeys.countries)) || [];

  const countries = storedCountries.length
    ? storedCountries
    : await fetch('https://restcountries.eu/rest/v2/all')
      .then(response => response.json());

  const selectElement = document.querySelector(`[name=${elements.selectCountry}]`);

  countries.forEach(country => {
    const option = document.createElement("option");

    option.value = country.name;
    option.text = country.name;

    selectElement.add(option);
  });

  localStorage.setItem(localStorageKeys.countries, JSON.stringify(countries));
}
