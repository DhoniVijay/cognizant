// Community Event Portal JavaScript

// 1. Log when page loads
window.onload = () => {
  console.log("Community Portal loaded successfully!");
  alert("Welcome to the Community Portal");
};

// 2. Save user event type preference
function savePreference() {
  const selected = document.getElementById("eventType").value;
  localStorage.setItem("preferredEventType", selected);
}

// 3. Load saved event type on page load
window.addEventListener("DOMContentLoaded", () => {
  const saved = localStorage.getItem("preferredEventType");
  if (saved) {
    document.getElementById("eventType").value = saved;
  }

  setupRegistrationForm();
  setupFeedbackForm();
});

// 4. Validate phone number on blur (reused in both forms)
function validatePhone() {
  const phoneInput = document.getElementById("phone");
  if (!phoneInput) return;
  const phone = phoneInput.value.trim();
  if (!/^\d{10}$/.test(phone)) {
    alert("Please enter a valid 10-digit phone number.");
    phoneInput.classList.add("error");
  } else {
    phoneInput.classList.remove("error");
  }
}

// 5. Show event fee on selection
function showFee() {
  const dropdown = document.getElementById("eventSelect");
  const fee = dropdown?.value;
  if (fee) {
    alert(`Selected event fee is $${fee}`);
  }
}

// 6. Registration Form Validation & Submission
function setupRegistrationForm() {
  const form = document.querySelector("form"); // First form is the registration form
  if (!form) return;

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = form.querySelector('input[name="name"]')?.value.trim();
    const email = form.querySelector('input[name="email"]')?.value.trim();
    const date = form.querySelector('input[name="date"]')?.value;
    const eventType = form.querySelector('select[name="eventType"], #eventType')?.value;
    const phone = form.querySelector('input[type="tel"]')?.value.trim();

    let errorMessage = "";

    if (!name) errorMessage += "Name is required.\n";
    if (!email || !/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(email)) errorMessage += "Valid email is required.\n";
    if (!date) errorMessage += "Event date is required.\n";
    if (!eventType) errorMessage += "Please select an event type.\n";
    if (!/^\d{10}$/.test(phone)) errorMessage += "Phone must be 10 digits.\n";

    if (errorMessage) {
      alert("Please fix the following:\n\n" + errorMessage);
      return;
    }

    alert("Thank you for registering!");
    document.getElementById("confirmation").textContent = "Registration successful!";
    form.reset();
  });
}

// 7. Count characters in feedback textarea
function countChars() {
  const textarea = document.getElementById("feedback");
  if (!textarea) return;
  const charCount = textarea.value.length;
  console.log(`Characters typed: ${charCount}`);
}

// 8. Enlarge event images on double-click
function enlargeImage(img) {
  img.classList.toggle("enlarged");
}

// 9. Video events
function videoReady() {
  const status = document.getElementById("videoStatus");
  status.textContent = "Video ready to play.";
}

function enlargeVideo(video) {
  video.style.transform = video.style.transform === "scale(1.3)" ? "scale(1)" : "scale(1.3)";
}

// 10. Geolocation
function findLocation() {
  const output = document.getElementById("locationOutput");
  if (!navigator.geolocation) {
    output.textContent = "Geolocation is not supported by your browser.";
    return;
  }

  output.textContent = "Locating...";

  navigator.geolocation.getCurrentPosition(
    (position) => {
      const { latitude, longitude } = position.coords;
      output.textContent = `Your location: ${latitude}, ${longitude}`;
    },
    (error) => {
      output.textContent = `Location error: ${error.message}`;
    },
    {
      enableHighAccuracy: true,
      timeout: 10000
    }
  );
}

// 11. Clear preferences
function clearPreferences() {
  localStorage.clear();
  sessionStorage.clear();
  document.getElementById("eventType").value = "";
  alert("Preferences cleared!");
}

// 12. Show confirmation for feedback
function showConfirmation() {
  alert("Feedback submitted. Thank you!");
}

// ✅ 13. Feedback Form Logic
function setupFeedbackForm() {
  const formList = document.querySelectorAll("form");
  const feedbackForm = formList[1]; // Assuming second form is feedback form
  if (!feedbackForm) return;

  const phoneInput = feedbackForm.querySelector("#phone");
  const eventSelect = feedbackForm.querySelector("#eventSelect");
  const feedbackTextarea = feedbackForm.querySelector("#feedback");

  // Validate feedback form on submit
feedbackForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const phone = phoneInput?.value.trim();
  const eventValue = eventSelect?.value;
  const message = feedbackTextarea?.value.trim();

  let errors = [];

  if (!/^\d{10}$/.test(phone)) {
    errors.push("Phone number must be 10 digits.");
  }

  if (!eventValue) {
    errors.push("Please select an event.");
  }

  if (!message) {
    errors.push("Feedback message cannot be empty.");
  }

  if (errors.length) {
    alert("Please correct the following:\n\n" + errors.join("\n"));
    return;
  }

  alert("Thank you for your feedback!");
  feedbackForm.reset();

  const counter = feedbackTextarea?.nextElementSibling;
  if (counter?.classList.contains("char-counter")) {
    counter.textContent = "500 characters remaining";
  }
});


  // Character counter setup
  feedbackTextarea?.addEventListener("input", () => {
    const maxLength = 500;
    const currentLength = feedbackTextarea.value.length;
    const remaining = maxLength - currentLength;

    let counter = feedbackTextarea.nextElementSibling;
    if (!counter || !counter.classList.contains("char-counter")) {
      counter = document.createElement("div");
      counter.className = "char-counter";
      feedbackTextarea.after(counter);
    }

    counter.textContent = `${remaining} characters remaining`;

    if (remaining < 0) {
      counter.classList.add("error");
    } else {
      counter.classList.remove("error");
    }
  });
}

// 14. jQuery enhancements (if jQuery is loaded)
if (typeof $ !== "undefined") {
  $(document).ready(function () {
    // Fade in banner
    $("#welcomeBanner").hide().fadeIn(1000);

    // Smooth scroll
    $("nav a").click(function (e) {
      if (this.hash !== "") {
        e.preventDefault();
        const hash = this.hash;
        $("html, body").animate({ scrollTop: $(hash).offset().top }, 800);
      }
    });

    // Image hover effect
    $(".event-img").hover(
      function () {
        $(this).fadeTo(200, 0.85);
      },
      function () {
        $(this).fadeTo(200, 1);
      }
    );
  });
}
