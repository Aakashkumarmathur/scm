console.log("script loaded");


let currentTheme = getTheme();
//initial
changeTheme(currentTheme)

function changeTheme() {
    //set to web page
    document.querySelector("html").classList.add(currentTheme);

    //set the listener
    const changeThemeButton = document.querySelector("#theme_change_button");
    changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";
    changeThemeButton.addEventListener("click", (event) => {
        console.log("change theme button clicked");
        const oldTheme = currentTheme;
        if(currentTheme == "dark"){
            //theme ko light
            currentTheme = "light";
        }
        else{
            //theme ko dark
            currentTheme = "dark";
        }

        //localStorge mein change krenge
        setTheme(currentTheme);
        //remove the current theme
        document.querySelector("html").classList.remove(oldTheme);
        //set the currentTheme
        document.querySelector("html").classList.add(currentTheme);
        //change theme text of button
        changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";

    } );
}


//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}


//get theme from local storage
function getTheme(){
    let theme = localStorage.getItem("theme");
    if(theme) return theme;
    else return "light"; 
}