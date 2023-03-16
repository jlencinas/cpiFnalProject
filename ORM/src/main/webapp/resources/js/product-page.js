function openOrderDetails() {
  document.getElementById("card-container1").innerHTML += `

  <div class="title">
    <h1>Order Details</h1>
</div>
<div class="inputFieldContainer">
    <div class="inputField">
        <div class="inputFieldLabel">
            <h2>Name</h2>
        </div>
        <div class="inputFieldInput">
            <div class="inputFieldName">
                <input type="text" placeholder="Ex: Jane" required>
                <p>First Name</p>
            </div>
            <div class="inputFieldName">
                <input type="text" placeholder="Ex: Doe" required>
                <p>Last Name</p>
            </div>
        </div>
    </div>
    <div class="inputField">
        <div class="inputFieldName">
            <input id="telField" class="telField" type="tel" placeholder="Ex: 09123456789" maxlength="11"
                minlength="11" pattern="[0-9]{11}" onchange="checkTelInput()" required>
            <span class="tel-validity tel-validity-hidden" id="telValidity"></span>
            <p>Mobile Number</p>
        </div>
    </div>

    <p class = "deliver-date-warning" id="delivery-date-warning"></p>
    <div class="deliveryDate">
        <div class="inputDateField">
            <input type="date" id="inputDate" name="inputdate" onchange="checkDeliveryDate(); boolDate();">
            <p>Deliver Date</p>
        </div>

        <div class="inputTimeField">
            <input type="time" class="timeField" id="inputTime" name="inputTime" onchange="checkTimeInput()"
                required disabled>
            <span class="time-validity time-validity-hidden" id="timeValidity"></span>
            <p>Deliver Time</p>
        </div>
    </div>

    <div class="orderSummary">
        <div class="inputFieldLabel">
            <h2>Summary of Order:</h2>
            <div class="listOfOrder">
            </div>
        </div>
    </div>
    <div class="buttonDiv">
        <button class="cancelButton" onclick="closePopup()">Cancel</button>
        <button class="orderButton">Order</button>
    </div>
</div>
    <script>
    </script>

`;

	console.log("The Document is Ready");
    checkDeliveryDate();
    checkTimeInput();
	boolDate();
}

function closeOrderDetails() {
  document.getElementById("card-container1").innerHTML = ``;
}


function checkDeliveryDate() {
  console.log("I'm clicked");
  //Date Constraintns
  var dtToday = new Date();
  var hour = dtToday.getHours();
  var minutes = dtToday.getMinutes();
  var month = dtToday.getMonth() + 1;
  var day = dtToday.getDate();
  var year = dtToday.getFullYear();
  var hourString =
    hour < 10
      ? (hourString = "0" + hour.toString())
      : (hourString = hour.toString());
  var minString =
    minutes < 10
      ? (minString = "0" + minutes.toString())
      : (minString = minutes.toString());
  var timeString = hourString + ":" + minString;

  if ((hour >= 18 && minutes >= 55) || hour > 18) {
    day = dtToday.getDate() + 1;
  }
  if (month < 10) month = "0" + month.toString();
  if (day < 10) day = "0" + day.toString();

  var maxDate = year + "-" + month + "-" + day;
  var dtRevised = new Date(maxDate);
  $("#inputDate").attr("min", maxDate);

  //Time Contraints
  const sourceDate = document.getElementById("inputDate");
  const sourceTime = document.getElementById("inputTime");
  console.log(sourceDate.value);
  if (sourceDate.value != "") {
    $("#inputTime").removeAttr("disabled");
    console.log("removed");
    const inputHandler = function (e) {
      console.log("changing");
      var dtInput = new Date(sourceDate.value);
      if (dtInput > dtRevised) {
        $("#inputTime").attr("min", "09:00");
        $("#inputTime").attr("max", "18:55");
      } else {
        if ((hour >= 18 && minutes >= 55) || hour > 18) {
          $("#inputTime").attr("min", timeString);
          $("#inputTime").attr("max", "18:55");
        } else if ((hour >= 10 && minutes >= 55) || hour > 10) {
          var minString;
          if (minutes < 10) {
            minString = "0" + minutes.toString();
          } else {
            minString = minutes.toString();
          }

          if (hour >= 12) {
            $("#inputTime").attr("min", timeString);
            $("#inputTime").attr("max", "18:55");
          } else {
            $("#inputTime").attr("min", "12:00");
            $("#inputTime").attr("max", "18:55");
          }
        } else {
          $("#inputTime").attr("min", "09:00");
          $("#inputTime").attr("max", "18:55");
        }
      }
    };
    sourceTime.addEventListener("input", inputHandler);
    sourceTime.addEventListener("propertychange", inputHandler);
  } else {
    console.log("Please select a delivery date first!");
  }
  // for IE8
}

function checkTelInput() {
  const source = document.getElementById("telField");
  const result = document.getElementById("telValidity");

  const inputHandler = function (e) {
    if (e.target.value == null || e.target.value == "") {
      result.classList.add("tel-validity-hidden");
    } else {
      result.classList.remove("tel-validity-hidden");
    }
  };
  source.addEventListener("input", inputHandler);
  source.addEventListener("propertychange", inputHandler); // for IE8
}


function checkTimeInput() {
  console.log("I'm clicked");
  const source = document.getElementById("inputTime");
  const result = document.getElementById("timeValidity");
  const inputHandler = function (e) {
    if (e.target.value == null || e.target.value == "") {
      result.classList.add("time-validity-hidden");
    } else {
      result.classList.remove("time-validity-hidden");
    }
  };
  source.addEventListener("input", inputHandler);
  source.addEventListener("propertychange", inputHandler); // for IE8
}

function boolDate() {
  const sourceDate = document.getElementById("inputDate");
  const sourceTime = document.getElementById("inputTime");
  const timeval = document.getElementById("timeValidity");
  console.log(sourceDate.value);
  if (sourceDate.value == null || sourceDate.value == "") {
    document.getElementById(
      "delivery-date-warning"
    ).innerHTML = `Please select a delivery date first! Before selecting delivery time.`;
  } else {
    document.getElementById("delivery-date-warning").innerHTML = ` `;
    sourceTime.value = "";
    timeval.classList.add("time-validity-hidden");
  }
}