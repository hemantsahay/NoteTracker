const submitBtn = document.getElementById("submit-btn");
const descEl = document.getElementById("description");
const titleEl = document.getElementById("title");

const addNote = function(){
    const apiUrl = '/notes';

    const postData = {
        title: titleEl.value,
        description: descEl.value
    };
    
    const fetchOptions = {  
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',   },
      body: JSON.stringify(postData) 
    };
    fetch(apiUrl, fetchOptions);
    titleEl.value = descEl.value = '';
}


submitBtn.addEventListener('click', function(e){
    e.preventDefault();
    addNote();
});

  