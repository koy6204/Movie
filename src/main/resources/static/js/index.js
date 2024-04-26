
const tmdbApiKey = '91ebb9f602d1cc08e13c77f2f2aa8ab0';
const maxAttempts = 100; // 최대 시도 횟수를 설정.

// const movieContainer = document.getElementById('movie-container');
// const trailerModal = document.getElementById('trailer-modal');
// const trailerIframe = document.getElementById('trailer-iframe');

// TMDB에서 인기 영화 목록을 가져오는 함수
async function fetchPopularMovies() {
    const url = `https://api.themoviedb.org/3/movie/popular?api_key=${tmdbApiKey}&language=ko-KR&page=1`;
    const response = await fetch(url);
    const data = await response.json();




    return data.results;

}

// 영화 ID로부터 예고편을 가져오는 함수
async function fetchTrailer(movieId) {
    const url = `https://api.themoviedb.org/3/movie/${movieId}/videos?api_key=${tmdbApiKey}&language=ko-KR`;
    const response = await fetch(url);
    const data = await response.json();
    const trailers = data.results.filter(video => video.type === 'Trailer');
    return trailers.length > 0 ? trailers[0] : null;
}

// 예고편을 재생하는 함수
function playTrailer(videoKey) {
    const trailerContainer = document.getElementById('trailer-container');
    trailerContainer.innerHTML = `<iframe src="https://www.youtube.com/embed/${videoKey}?autoplay=0"; encrypted-media" allowfullscreen></iframe>`;
}

// 랜덤한 영화의 예고편을 재생하는 함수
async function playRandomTrailer(attempt = 1) {
    if (attempt > maxAttempts) {
        console.error('예고편을 찾을 수 없습니다.');
        return;
    }

    try {
        const movies = await fetchPopularMovies();
        const randomIndex = Math.floor(Math.random() * movies.length);
        const movie = movies[randomIndex];
        const trailer = await fetchTrailer(movie.id);

        if (trailer) {
            playTrailer(trailer.key);
        } else {
            // 예고편을 찾을 수 없으면 다른 랜덤한 영화로 시도.
            playRandomTrailer(attempt + 1);
        }
    } catch (error) {
        console.error('An error occurred:', error);
    }
}





// TMDB에서 인기 영화 목록을 가져오는 함수
// function fetchPopularMovies() {
//     // const url = `https://api.themoviedb.org/3/movie/popular?api_key=${tmdbApiKey}&language=ko-KR&page=1`;
//     fetch(`https://api.themoviedb.org/3/movie/popular?api_key=${tmdbApiKey}&language=ko-KR&page=1`)
//
//         .then(response => response.json())
//         .then(data => {
//             data.results.forEach(movie => {
//                 const movieEl = document.createElement('div');
//                 movieEl.classList.add('movie');
//                 movieEl.innerHTML = `
//                     <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
//                     <h3>${movie.title}</h3>
//                 `;
//                 movieEl.addEventListener('click', () => {
//                     fetchTrailer(movie.id);
//                 });
//                 movieContainer.appendChild(movieEl);
//             });
//         });
// }
//
// // 선택한 영화의 예고편을 가져오는 함수
// function fetchTrailer(movieId) {
//     fetch(`https://api.themoviedb.org/3/movie/${movieId}/videos?api_key=${tmdbApiKey}&language=ko-KR`)
//         .then(response => response.json())
//         .then(data => {
//             const trailer = data.results.find(video => video.type === 'Trailer');
//             if (trailer) {
//                 const youtubeUrl = `https://www.youtube.com/embed/${trailer.key}`;
//                 trailerIframe.src = youtubeUrl;
//                 trailerModal.style.display = 'flex';
//             } else {
//                 alert('예고편을 찾을 수 없습니다.');
//             }
//         });
// }
//
// // 모달 닫기 함수
// function closeModal() {
//     trailerModal.style.display = 'none';
//     trailerIframe.src = '';
// }



// 페이지 로드 시 랜덤 인기 영화 예고편을 자동으로 재생.
window.addEventListener('load', () => playRandomTrailer());
// fetchPopularMovies();







