const BASE_URL = "http://localhost:8080/api/oil";

function loadDashboard() {

    // अभी hardcoded userId = 1 (later dynamic करेंगे)
    fetch(BASE_URL + "/dashboard-summary?role=RESTAURANT&userId=1")
        .then(res => res.json())
        .then(data => {

            console.log(data);

            // ✅ COUNTS UPDATE
            document.getElementById("approved").innerText = data.approved;
            document.getElementById("pending").innerText = data.pending;
            document.getElementById("total").innerText = data.total;

            // ✅ TABLE UPDATE
            let table = document.getElementById("tableBody");
            table.innerHTML = "";

            data.data.forEach(item => {
                let row = `
                    <tr>
                        <td>${item.requestDate}</td>
                        <td>${item.quantity}</td>
                        <td>${item.status}</td>
                        <td>${item.price ?? "-"}</td>
                    </tr>
                `;
                table.innerHTML += row;
            });

        });
}