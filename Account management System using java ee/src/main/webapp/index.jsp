<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Bank Account</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4>Create Bank Account</h4>
        </div>
        <div class="card-body">
            <form id="accountForm">
                <div class="mb-3">
                    <label for="accountNumber" class="form-label">Account Number</label>
                    <input type="text" class="form-control" id="accountNumber" required>
                </div>
                <div class="mb-3">
                    <label for="accountHolderName" class="form-label">Account Holder's Name</label>
                    <input type="text" class="form-control" id="accountHolderName" required>
                </div>
                <div class="mb-3">
                    <label for="accountType" class="form-label">Account Type</label>
                    <select class="form-select" id="accountType" required>
                        <option value="">Select type</option>
                        <option value="Savings">Savings</option>
                        <option value="Current">Current</option>

                    </select>
                </div>
                <div class="mb-3">
                    <label for="accountBalance" class="form-label">Account Balance</label>
                    <input type="number" step="0.01" class="form-control" id="accountBalance" required>
                </div>
                <button type="submit" class="btn btn-success">Create Account</button>
            </form>
        </div>
        <div class="card-footer" id="resultMessage"></div>
    </div>
</div>

<script>
    document.getElementById("accountForm").addEventListener("submit", function (e) {
        e.preventDefault();

        const accountData = {
            accountNumber: document.getElementById("accountNumber").value.trim(),
            accountHolderName: document.getElementById("accountHolderName").value.trim(),
            accountType: document.getElementById("accountType").value,
            accountBalance: parseFloat(document.getElementById("accountBalance").value)
        };

        if (accountData.accountBalance < 0) {
            document.getElementById("resultMessage").innerHTML = '<span class="text-danger">Balance cannot be negative.</span>';
            return;
        }

        fetch("accountservlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(accountData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    document.getElementById("resultMessage").innerHTML = `<span class="text-success">${data.message}</span>`;
                    document.getElementById("accountForm").reset();
                } else {
                    document.getElementById("resultMessage").innerHTML = `<span class="text-danger">${data.message}</span>`;
                }
            })
            .catch(error => {
                document.getElementById("resultMessage").innerHTML = `<span class="text-danger">Error: ${error}</span>`;
            });
    });
</script>


</body>
</html>


