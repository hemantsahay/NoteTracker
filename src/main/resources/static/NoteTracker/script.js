const noteContainer = document.getElementById("noteContainer");
const saveModelBtn = document.getElementById("save-model");
const descEl = document.getElementById("description");
const titleEl = document.getElementById("title");
const noteId = document.getElementById("NoteId");
const descInsertEl = document.getElementById("description-insert");
const titleInsertEl = document.getElementById("title-insert");
noteContainer.innerHTML= '';

const insertNotes = function(note){
    let html= `
    <div class="col-sm-4 mb-10" data-toggle="modal" data-target="#updateNote" onclick="UpdateCard(${note.id})">
      <div class="panel panel-primary notes">
        <div class="panel-heading">${note.title}
        <button type="button" onclick="deleteCard(${note.id})" id="delete-${note.id}" class="close" data-dismiss="modal">&times;</button></div>
        <div class="panel-body">${note.description}</div>
        
        </div>
    </div>
    `;

    noteContainer.insertAdjacentHTML("beforeend",html);
}
const getAllNotes = function(){
    fetch("/notes")
    .then(r => r.json())
    .then(notes => notes.forEach(item => insertNotes(item)))
}

const updateNote = function(id){
    console.log('update is called with short URL')
    const apiUrl = `/notes/${id}`;

    const postData = {
        title: titleEl.value,
        description: descEl.value
    };
    
    const fetchOptions = {  
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',   },
      body: JSON.stringify(postData) 
    };
    fetch(apiUrl, fetchOptions);
    
}

const deleteCard = function(id){
    fetch(`/notes/${id}`,{method: 'DELETE'})
    location.reload();
}

const UpdateCard = function(id){
    console.log('update button is working')
    fetch(`/notes/${id}`)
    .then(r => r.json())
    .then(notes => {
        titleEl.value = notes.title
        descEl.value = notes.description  
        noteId.value = notes.id; 
    })
    
}

const saveNote = function(){
    updateNote(noteId.value);
    location.reload();
} 

const insertNote = function(){
    console.log('Save is Clicked')
    const apiUrl = '/notes';

    const postData = {
        title: titleInsertEl.value,
        description: descInsertEl.value
    };
    
    const fetchOptions = {  
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',   },
      body: JSON.stringify(postData) 
    };
    fetch(apiUrl, fetchOptions);
    titleEl.value = descEl.value = '';
    location.reload();
}

getAllNotes();