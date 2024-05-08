

window.onload = function() {
    var menu1 = document.getElementById('menu1');
    var menu2 = document.getElementById('menu2');

    menu1.addEventListener('click', function() {
        selectMenu(menu1);
        hideAllContent();
        document.getElementById('content1').style.display = 'block';
    });

    menu2.addEventListener('click', function() {
        selectMenu(menu2);
        hideAllContent();
        document.getElementById('content2').style.display = 'block';
    });

    function hideAllContent() {
        var contents = document.getElementsByClassName('content');
        for (var i = 0; i < contents.length; i++) {
            contents[i].style.display = 'none';
        }
    }

    function selectMenu(selectedMenu) {
        // 모든 메뉴 항목에서 'selected' 클래스 제거
        var menus = document.getElementsByTagName('td');
        for (var i = 0; i < menus.length; i++) {
            menus[i].classList.remove('selected');
        }

        // 선택된 메뉴 항목에 'selected' 클래스 추가
        selectedMenu.classList.add('selected');
    }

    // 페이지가 로드될 때 "나의 정보" 메뉴의 내용을 표시하고 선택
    hideAllContent();
    document.getElementById('content1').style.display = 'block';
    selectMenu(menu1);
}