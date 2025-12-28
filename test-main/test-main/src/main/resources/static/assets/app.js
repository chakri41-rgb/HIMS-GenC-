function login() {
  alert("Login clicked (connect backend later)");
}

function registerUser() {
  alert("Register clicked (connect backend later)");
}
function openPolicy(id) {
  window.location.href = "policy-details.html?id=" + id;
}
function submitClaim() {
  alert("Claim submitted successfully (backend later)");
}

function payNow() {
  alert("Redirecting to payment gateway (mock)");
}
function createTicket() {
  alert("Support ticket created (backend later)");
}

function saveProfile() {
  alert("Profile updated successfully");
}
function approveAction(type) {
  alert(type + " approved");
}

function rejectAction(type) {
  alert(type + " rejected");
}

function exportCSV() {
  alert("Exporting CSV (mock)");
}

function saveSettings() {
  alert("Settings saved");
}

// Fetch current profile and update UI (for static pages)
async function initUserProfile() {
  try {
    const res = await fetch('/api/auth/profile', { credentials: 'include' });
    if(res.ok) {
      const user = await res.json();
      document.querySelectorAll('.js-username').forEach(el => el.textContent = user.username);
      // show/hide auth buttons
      document.querySelectorAll('.js-authenticated').forEach(el => el.classList.remove('hidden'));
      document.querySelectorAll('.js-unauthenticated').forEach(el => el.classList.add('hidden'));
    } else {
      // not logged in
      document.querySelectorAll('.js-authenticated').forEach(el => el.classList.add('hidden'));
      document.querySelectorAll('.js-unauthenticated').forEach(el => el.classList.remove('hidden'));
    }
  } catch (err) {
    console.error('Failed to fetch profile', err);
  }
}

// Run on page load
if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', initUserProfile);
} else {
  initUserProfile();
}
