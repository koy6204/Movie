const tmdbApiKey = 'YOUR_TMDB_API_KEY';
const youtubeApiKey = 'YOUR_YOUTUBE_API_KEY';

// TMDB에서 인기 영화 목록을 가져오는 함수
async function fetchPopularMovies() {
    const response = await fetch(`https://api.themoviedb.org/3/movie/popular?api_key=${tmdbApiKey}&language=ko-KR&page=1`);
    const data = await response.json();
    return data.results; // 영화 목록 반환
}

// YouTube에서 영화 트레일러를 검색하는 함수
async function searchYoutubeTrailer(movieTitle) {
    const youtubeSearchUrl = `https://www.googleapis.com/youtube/v3/search?part=snippet&q=${movieTitle}+trailer&key=${youtubeApiKey}`;
    const response = await fetch(youtubeSearchUrl);
    const data = await response.json();
    const videoId = data.items[0].id.videoId; // 첫 번째 검색 결과의 비디오 ID
    playTrailer(videoId); // 트레일러 재생
}

// YouTube에서 트레일러를 재생하는 함수
function playTrailer(videoId) {
    const iframe = document.createElement('iframe');
    iframe.src = `https://www.youtube.com/embed/${videoId}`;
    iframe.width = '560';
    iframe.height = '315';
    document.body.appendChild(iframe); // iframe을 문서에 추가하여 트레일러 재생
}

// 실행 예제
fetchPopularMovies().then(movies => {
    movies.forEach(movie => {
        searchYoutubeTrailer(movie.title); // 각 영화 제목으로 트레일러 검색
    });
});
