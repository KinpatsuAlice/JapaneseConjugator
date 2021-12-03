function toKana(text) {
  text.value = wanakana.toKana(text.value, options = { customKanaMapping: { nn: 'ん' , n:'n'}});
}

function waitForEnter() {
  return new Promise((resolve) => {
    document.addEventListener("keydown", onKeyHandler);
    function onKeyHandler(e) {
      if (e.keyCode == 13) {
        document.removeEventListener("keydown",onKeyHandler);
        resolve();
      }
    }
  });
}

function changeSettings(checkbox) {
  var setting = document.getElementById(checkbox.value);
  if (checkbox.checked)
    setting.style.display = "none";
  else
    setting.style.display = "block";
}
