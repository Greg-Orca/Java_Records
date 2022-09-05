let audio;

function playMusic(album){
    let albumId = album.getAttribute('data');
    audio = new Audio(`static/music/music_${albumId}.mp3`);
    console.log(albumId);
    audio.currentTime=60;
    audio.play();
}

function playMusicEventListener(){
    let albums = document.getElementsByName("playmusic");
    for (let i = 0; i < albums.length; i++) {
        albums[i].addEventListener("click", playMusicEventHandler)
    }
}

function playMusicEventHandler(e){
    let album = e.currentTarget;
    console.log(album);
    console.log(album);
    if(album.getAttribute("play")){
        console.log("asd")
        album.removeAttribute("play");
        stopMusic();
    }else{
        playMusic(album);
        album.setAttribute("play", "yes");
    }

}

function stopMusic(){
    audio.pause();
}


playMusicEventListener();

