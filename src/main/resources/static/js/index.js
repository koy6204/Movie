
const tmdbApiKey = '91ebb9f602d1cc08e13c77f2f2aa8ab0';
const maxAttempts = 100; // 최대 시도 횟수를 설정.


// TMDB에서 인기 영화 목록을 가져오는 함수
async function fetchPopularMovies() {
    const url = `https://api.themoviedb.org/3/movie/popular?api_key=${tmdbApiKey}&language=ko-KR&page=1`;
    const response = await fetch(url);
    const data = await response.json();

    return data.results;

}

//최고평점 영화 목록 가져오기
async function fetchTopRatedMovies() {

    const url = `https://api.themoviedb.org/3/movie/top_rated?api_key=${tmdbApiKey}&language=ko-KR&page=1`;
    const response = await fetch(url);
    const data = await response.json();

    return data.results;

}

//특정 영화 정보 가져오기
async function fetchMovie(movieId) {
    const url = `https://api.themoviedb.org/3/movie/${movieId}?api_key=${tmdbApiKey}&language=ko-KR&append_to_response=credits`;
    const response = await fetch(url);
    const data = await response.json();

    return data;

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

// 페이지 로드 시 랜덤 인기 영화 예고편을 자동으로 재생.
window.addEventListener('load', () => playRandomTrailer());

//인기영화 목록을 표시
async function displayPopularMovies() {
    const movies = await fetchPopularMovies();
    const movieContainer = document.getElementById('movie-container');

    for (let i = 0; i < 4 && i < movies.length; i++) {
        const movie = movies[i];
        movieContainer.innerHTML += `
            <div class="trailer1">
                <a href="movie/${movie.id}" onclick="event.preventDefault(); showModal(${movie.id});" class="test">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                    <div class="movie-title">${movie.title}</div>
                </a>
            </div>
        `;

    }
}

//최고평점 영화 목록을 표시
async function displayTopRatedMovies() {
    const movies = await fetchTopRatedMovies();
    const movieContainer = document.getElementById('movie-container2');

    for (let i = 0; i < 4 && i < movies.length; i++) {
        const movie = movies[i];
        movieContainer.innerHTML += `
            <div class="trailer1">
                <a href="movie/${movie.id}" onclick="event.preventDefault(); showModal(${movie.id});">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                    <div class="movie-title">${movie.title}</div>
                </a>
            </div>
        `;
    }

}

// 모달 창을 표시하는 함수
async function showModal(movieId) {
    document.getElementById('myModal').style.display = 'block';
    const trailer = await fetchTrailer(movieId);
    const movie = await fetchMovie(movieId);
    if (trailer) {
        const modalMovie = document.getElementById('modal-video');
        modalMovie.innerHTML = `<iframe src="https://www.youtube.com/embed/${trailer.key}?autoplay=0"; encrypted-media" allowfullscreen ></iframe>
                               
                                <div id="movie-info">
                                    <br>
                                    <div class="image-info">
                                        <div class="content-title-image" >
                                            <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" style="width: 100px" />
                                        
                                        </div>
                                        <div class="content-title">
                                             <h3>${movie.title}</h3>
                                            <p>개봉일: ${movie.release_date}</p>
                                            <p>평점: ${movie.vote_average}</p>
                                            <p>장르: ${movie.genres.map(genre => genre.name).join(', ')}</p>
                                            <p>제작사: ${movie.production_companies.map(company => company.name).join(', ')}</p>
                                            <p>감독: ${movie.credits.crew.filter(crew => crew.job === 'Director').map(crew => crew.name).join(', ')}</p>
                                        </div>
                                    </div>
                                 
                                    <p>${movie.overview}</p>
                                    <br>
                                    <h3>CAST</h3>
                                    <div class="actor-list">
                                        ${movie.credits.cast.slice(0, 5).map(actor => `
                                            <div>
                                                <img class="actor-img" src="https://image.tmdb.org/t/p/w500${actor.profile_path}" alt="${actor.name}" />
                                                <p>${actor.name}</p>
                                            </div>
                                        `).join('')}
                          
                                    </div>
                                    
                                </div>`;


    } else {
        alert('예고편을 찾을 수 없습니다.');
        const modalMovie = document.getElementById('modal-video');
        modalMovie.innerHTML = `                      
                                <div id="movie-info">
                                    <br>
                                    <div class="image-info">
                                        <div class="content-title-image" >
                                            <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" style="width: 100px" />
                                        
                                        </div>
                                        <div class="content-title">
                                             <h3>${movie.title}</h3>
                                            <p>개봉일: ${movie.release_date}</p>
                                            <p>평점: ${movie.vote_average}</p>
                                            <p>장르: ${movie.genres.map(genre => genre.name).join(', ')}</p>
                                            <p>제작사: ${movie.production_companies.map(company => company.name).join(', ')}</p>
                                            <p>감독: ${movie.credits.crew.filter(crew => crew.job === 'Director').map(crew => crew.name).join(', ')}</p>
                                        </div>
                                    </div>
                                 
                                    <p>${movie.overview}</p>
                              
                                    <h3>CAST</h3>
                                    <div class="actor-list">
                                        ${movie.credits.cast.slice(0, 5).map(actor => `
                                            <div>
                                                <img class="actor-img" src="https://image.tmdb.org/t/p/w500${actor.profile_path}" alt="${actor.name}" />
                                                <p>${actor.name}</p>
                                            </div>
                                        `).join('')}
                          
                                    </div>
                                
                                </div>`;
    }
}
// 모달창 닫기
function closeModal() {
    //영상끄기
    var iframe = document.querySelector('#myModal iframe');
    if (iframe) {
        iframe.src = iframe.src;
    }

    document.getElementById('myModal').style.display = 'none';
}


window.addEventListener('load', () => playRandomTrailer(), displayPopularMovies(), displayTopRatedMovies());