const apiUrl = "http://localhost:8080/contacts";

const contactsContainer = document.getElementById("contactsContainer");
const addContactButton = document.getElementById("addContactButton");

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
            <button onclick="deleteContact(${contact.id})">
                Löschen
            </button>

            <hr>
        `;

        contactsContainer.appendChild(contactElement);
    }
}

async function addContact() {
    const nameInput = document.getElementById("nameInput");
    const emailInput = document.getElementById("emailInput");
    const phoneInput = document.getElementById("phoneInput");

    const contact = {
        name: nameInput.value,
        email: emailInput.value,
        phoneNumber: phoneInput.value
    };

    await fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(contact)
    });

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

loadContacts();