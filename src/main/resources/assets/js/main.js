const googleContent = document.getElementById('main-js').getAttribute('content');
const signin = document.getElementById('google-signin');
const signout = document.getElementById('signout');
let instance;

window.onload = () => {
  gapi.load('auth2', function () {
    instance = gapi.auth2.init({
      client_id: `${googleContent}`,
      scope: 'profile email',
      fetch_basic_profile: true
    })
    if (!instance.isSignedIn.get()) {
      renderButton();
    } else {
      signout.style.display = 'block';
    }
  })
}

signout.onclick = () => {
  if (instance.isSignedIn.get()) {
    instance.disconnect();
    signout.style.display = 'none';
    signin.style.display = 'block';
  }
}

const onSuccess = (user) => {
  signin.style.display = 'none';
  signout.style.display = 'block';
}

const onFailure = (error) => {
  console.log(error);
}

const renderButton = () => {
  gapi.signin2.render('google-signin', {
    'scope': 'profile email',
    'width': 180,
    'height': 25,
    'longtitle': true,
    'theme': 'dark',
    'onsuccess': onSuccess,
    'onfailure': onFailure
  });
}