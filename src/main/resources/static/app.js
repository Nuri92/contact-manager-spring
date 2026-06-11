const apiUrl = "http://localhost:8080/contacts";

const contactsContainer = document.getElementById("contactsContainer");
const addContactButton = document.getElementById("addContactButton");
const errorContainer = document.getElementById("errorContainer");
let editingContactId = null;

addContactButton.addEventListener("click", addContact);

async function loadContacts() {
    const response = await fetch(apiUrl);
    const contacts = await response.json();

    contactsContainer.innerHTML = "";

    for (const contact of contacts) {
        const contactElement = document.createElement("div");

        contactElement.innerHTML = `
            <strong>${contact.name}</strong><br>
            ${contact.email}<br>
            ${contact.phoneNumber}<br>
            Favorit: ${contact.favorite}<br>
            <button onclick="toggleFavorite(${contact.id})">
                ${contact.favorite ? "Entfavorisieren" : "Favorisieren"}
            </button>
            <button onclick="deleteContact(${contact.id})">
                Löschen
            </button>
            <button onclick="startEditContact(${contact.id}, '${contact.name}', '${contact.email}', '${contact.phoneNumber}')">
                Bearbeiten
            </button>

            <hr>
        `;

        contactsContainer.appendChild(contactElement);
    }
}

async function addContact() {
    clearErrors();

    const nameInput = document.getElementById("nameInput");
    const emailInput = document.getElementById("emailInput");
    const phoneInput = document.getElementById("phoneInput");

    const contact = {
        name: nameInput.value,
        email: emailInput.value,
        phoneNumber: phoneInput.value
    };

    let response;

    if (editingContactId === null) {
        response = await fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contact)
        });
    } else {
        response = await fetch(`${apiUrl}/${editingContactId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contact)
        });
    }

    if (!response.ok) {
        const errorResponse = await response.json();
        showErrors(errorResponse.errors);
        return;
    }

    editingContactId = null;
    addContactButton.textContent = "Kontakt hinzufügen";

    nameInput.value = "";
    emailInput.value = "";
    phoneInput.value = "";

    await loadContacts();
}

async function deleteContact(id) {
    await fetch(
        `${apiUrl}/${id}`,
        {
            method: "DELETE"
        }
    );

    await loadContacts();
}

async function toggleFavorite(id) {
    await fetch(
        `${apiUrl}/${id}/favorite`,
        {
            method: "PATCH"
        }
    );

    await loadContacts();
}

function startEditContact(id, name, email, phoneNumber) {
    editingContactId = id;

    document.getElementById("nameInput").value = name;
    document.getElementById("emailInput").value = email;
    document.getElementById("phoneInput").value = phoneNumber;

    addContactButton.textContent = "Kontakt aktualisieren";
}

function clearErrors() {
    errorContainer.innerHTML = "";
}

function showErrors(errors) {
    errorContainer.innerHTML = "";

    for (const error of errors) {
        const errorElement = document.createElement("p");
        errorElement.textContent = error;
        errorContainer.appendChild(errorElement);
    }
}

loadContacts();