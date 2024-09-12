let code = 1;

function loadCategories() {
  const savedCategories = JSON.parse(localStorage.getItem("categories")) || [];
  const table = document.querySelector("table");

  savedCategories.forEach((category) => {
    const newRow = table.insertRow();

    const cellCode = newRow.insertCell(0);
    const cellCategory = newRow.insertCell(1);
    const cellTax = newRow.insertCell(2);
    const cellAction = newRow.insertCell(3);

    cellCode.textContent = category.code;
    cellCategory.textContent = category.name;
    cellTax.textContent = `${category.tax}%`;

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.onclick = function () {
      table.deleteRow(newRow.rowIndex);
      deleteCategory(category.code);
    };
    cellAction.appendChild(deleteButton);
  });

  code = savedCategories.length + 1;
}

function cadastraCategoria(event) {
  event.preventDefault();

  const categoryName = document.getElementById("CatName").value;
  const taxValue = document.getElementById("tax").value;

  if (!categoryName || !taxValue) {
    alert("Por favor, preencha todos os campos.");
    return;
  }

  const table = document.querySelector("table");
  const newRow = table.insertRow();

  const cellCode = newRow.insertCell(0);
  const cellCategory = newRow.insertCell(1);
  const cellTax = newRow.insertCell(2);
  const cellAction = newRow.insertCell(3);

  const currentCode = `00${code++}`;

  cellCode.textContent = currentCode;
  cellCategory.textContent = categoryName;
  cellTax.textContent = `${taxValue}%`;

  const deleteButton = document.createElement("button");
  deleteButton.textContent = "Delete";
  deleteButton.onclick = function () {
    table.deleteRow(newRow.rowIndex);
    deleteCategory(currentCode);
  };
  cellAction.appendChild(deleteButton);

  saveCategory({ code: currentCode, name: categoryName, tax: taxValue });

  document.getElementById("CatName").value = "";
  document.getElementById("tax").value = "";
}

function saveCategory(category) {
  const categories = JSON.parse(localStorage.getItem("categories")) || [];
  categories.push(category);
  localStorage.setItem("categories", JSON.stringify(categories));
}

function deleteCategory(code) {
  let categories = JSON.parse(localStorage.getItem("categories")) || [];
  categories = categories.filter((category) => category.code !== code);
  localStorage.setItem("categories", JSON.stringify(categories));
}

window.onload = loadCategories;

document.querySelector("form").addEventListener("submit", cadastraCategoria);
