const tmdbApiKey = '91ebb9f602d1cc08e13c77f2f2aa8ab0';
let selectedMovies = []; // 선택된 영화의 ID를 저장할 배열
async function fetchTopRatedMovies() {
    let allMovies = [];
    for (let page = 1; page <= 3; page++) {
        const url = `https://api.themoviedb.org/3/movie/top_rated?api_key=${tmdbApiKey}&language=ko-KR&page=${page}`;
        const response = await fetch(url);
        const data = await response.json();
        allMovies = allMovies.concat(data.results);
    }
    return allMovies;
}

//최고평점 영화 목록을 표시
async function displayTopRatedMovies() {
    const movies = await fetchTopRatedMovies();

    for (let i = movies.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [movies[i], movies[j]] = [movies[j], movies[i]];
    }

    const movieContainer = document.getElementById('movie-container');

    for (let i = 0; i < 60 && i < movies.length; i++) {
        const movie = movies[i];
        movieContainer.innerHTML += `
            <div class="movie-poster">
               
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" tabindex="0" onclick="toggleMovieSelection(${movie.id})">
                    <div class="movie-title">${movie.title}</div>
              
            </div>
        `;
    }
}


function toggleMovieSelection(movieId) {
    const index = selectedMovies.indexOf(movieId);
    alert(movieId);
    if (index > -1) {
        // 이미 선택된 영화라면 선택 취소
        selectedMovies.splice(index, 1);
        alert("영화취소댐")
    } else {
        // 아직 선택되지 않은 영화라면 선택
        // 단, 이미 10개의 영화가 선택되어 있다면 추가로 선택하지 못하도록 함
        if (selectedMovies.length < 10) {
            selectedMovies.push(movieId);
        } else {
            alert('최대 10개의 영화를 선택할 수 있습니다.');
        }
    }
}

// 영화 포스터를 클릭하면 선택/선택 취소
// document.addEventListener('DOMContentLoaded', (event) => {
//     const movieContainer = document.getElementById('movie-container');
//     if (movieContainer) {
//         movieContainer.addEventListener('click', (event) => {
//             const moviePoster = event.target.closest('.movie-poster');
//             if (moviePoster) {
//                 const movieId = moviePoster.dataset.movieId;
//                 toggleMovieSelection(movieId);
//             }
//         });
//     }
// });


window.addEventListener('load', () => displayTopRatedMovies());