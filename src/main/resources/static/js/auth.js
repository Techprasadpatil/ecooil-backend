const BASE_URL = "http://localhost:8080/api/auth";

// ✅ REGISTER
function register() {

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    fetch(BASE_URL + "/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            email: email,
            password: password,
            role: role
        })
    })
    .then(res => res.json())
    .then(() => {
        alert("Registration Successful ✅");
        window.location.href = "login.html";
    })
    .catch(err => {
        console.error(err);
        alert("Registration Failed ❌");
    });
}


// ✅ LOGIN
function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(BASE_URL + "/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(res => res.json())
    .then(data => {


        localStorage.setItem("userId", data.id);


        const role = (data.role || "").toUpperCase().trim();
        localStorage.setItem("role", role);

        console.log("Login role:", role); // 🔥 debug


        if (role === "COMPANY") {
            window.location.href = "company-dashboard.html";
        } else {
            window.location.href = "owner-dashboard.html";
        }

    })
    .catch(err => {
        console.error(err);
        alert("Login Failed ❌");
    });
}