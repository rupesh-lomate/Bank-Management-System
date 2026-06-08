<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ATM</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .atm-container {
            position: relative;
            width: 700px;
            height: 550px;
            background: url('1000_F_128377193_9BIJQsBm0wkZNroIE10JmBmTN2ureGgd.jpg') no-repeat center center;
            background-size: cover;
        }
        .atm-screen {
            position: absolute;
            top: 19%;
            left: 6%;
            width: 50%;
            height: 47%;
            background-color: #1976d2;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            font-size: 18px;
            font-weight: bold;
            border-radius: 10px;
            box-shadow: inset 0px 5px 20px black;
            flex-direction: column;
            text-align: center;
            padding: 20px;
        }
        input {
            width: 80%;
            padding: 10px;
            margin: 10px 0;
            font-size: 16px;
            text-align: center;
        }
        .btn {
            width: 80%;
            padding: 10px;
            background-color: #fbc02d;
            border: none;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
            margin: 5px 0;
        }
        .btn:hover {
            background-color: #ff8f00;
        }
    </style>
</head>
<body>

<div class="atm-container">
    <div class="atm-screen" id="atm-screen">
        <p>Welcome to Swarajya ATM</p>
        <p>Please Enter Your Debit Card Number</p>
        <input type="text" id="cardNumber" placeholder="Enter 16-digit card number">
        <button class="btn" onclick="validateCard()">Submit</button>
    </div>
</div>

<script>
    function validateCard() {
        let cardNumber = document.getElementById('cardNumber').value;
        if (cardNumber.length === 16 && !isNaN(cardNumber)) {
            showOptions();
        } else {
            alert("Invalid card number. Please enter a valid 16-digit number.");
        }
    }

    function showOptions() {
        document.getElementById('atm-screen').innerHTML = `
            <p>Select an Option</p>
            <button class="btn" onclick="generatePin()">Generate PIN</button>
            <button class="btn" onclick="showBankingOptions()">Banking</button>
        `;
    }

    function generatePin() {
        document.getElementById('atm-screen').innerHTML = `
            <p>Enter New 4-digit PIN</p>
            <input type="password" id="newPin" placeholder="Enter PIN">
            <button class="btn" onclick="confirmPin()">Submit</button>
        `;
    }

    function confirmPin() {
        let newPin = document.getElementById('newPin').value;
        if (newPin.length === 4 && !isNaN(newPin)) {
            alert("PIN Generated Successfully!");
            showOptions();
        } else {
            alert("Invalid PIN. Please enter a 4-digit number.");
        }
    }

    function showBankingOptions() {
        document.getElementById('atm-screen').innerHTML = `
            <p>Banking Options</p>
            <button class="btn" onclick="withdrawCash()">Withdraw Cash</button>
            <button class="btn" onclick="checkBalance()">Balance Inquiry</button>
            <button class="btn" onclick="miniStatement()">Mini Statement</button>
            <button class="btn" onclick="showOptions()">Back</button>
        `;
    }

    function withdrawCash() {
        let amount = prompt("Enter withdrawal amount:");
        if (amount > 0) {
            alert("Processing ₹" + amount);
            document.getElementById('atm-screen').innerHTML = `<p>Collect your cash</p><button class="btn" onclick="showOptions()">Back</button>`;
        }
    }

    function checkBalance() {
        document.getElementById('atm-screen').innerHTML = `<p>Your Balance is ₹50,000</p><button class="btn" onclick="showOptions()">Back</button>`;
    }

    function miniStatement() {
        document.getElementById('atm-screen').innerHTML = `<p>Mini Statement:</p><p>Last 5 transactions</p><button class="btn" onclick="showOptions()">Back</button>`;
    }
</script>

</body>
</html>
