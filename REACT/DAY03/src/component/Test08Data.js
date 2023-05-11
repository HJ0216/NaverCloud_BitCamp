export default [
    {id: 1, img: './image/image01.jpg', title: 'puppy01'},
    {id: 2, img: './image/image02.jpg', title: 'puppy02'},
    {id: 3, img: './image/image03.jpg', title: 'puppy03'},
    {id: 4, img: './image/image04.jpg', title: 'puppy04'},
    {id: 5, img: './image/image05.jpg', title: 'puppy05'},
    {id: 6, img: './image/image06.jpg', title: 'puppy06'},
    {id: 7, img: './image/image07.jpg', title: 'puppy07'}
    /* .: 현재위치 = index.html이 있는 public 폴더 */
]

/*
- public에 있는 이미지 폴더는 index.html를 기준(/public)으로부터 상대경로를 지정해야 한다.
- index.html 안의 <div id="root"></div> 이곳으로 렌더링 되기 때문이다.
- 단 ./ 를 생략해서는 안 된다.
*/