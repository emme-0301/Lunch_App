document.getElementById("recommend-btn").addEventListener("click", function() {
    fetch("/recommend")
        .then(response => response.json())
        .then(data => {
            const resultArea = document.getElementById("recommend-result");
            resultArea.innerHTML = `<strong>おすすめ:</strong> ${data.menuName}（${data.cost}円）`;
        });
});
