------------------------------------------------------------------------------------------------------------------------
Console usability:
------------------------------------------------------------------------------------------------------------------------
// GET all
fetch('/info/').then(response => response.json().then(console.log))
------------------------------------------------------------------------------------------------------------------------
// GET one by id
fetch('/info/id/1').then(response => response.json().then(console.log))
------------------------------------------------------------------------------------------------------------------------
// GET all by city
fetch('/info/city/Москва').then(response => response.json().then(console.log))
------------------------------------------------------------------------------------------------------------------------
// POST add new one
fetch(
  '/info',
  {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ city: 'Мир', info: 'Мирский замок' })
  }
).then(result => result.json().then(console.log))
------------------------------------------------------------------------------------------------------------------------
// PUT save existing by id
fetch(
  '/info/id/11',
  {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ city: 'Мир', info: 'Мирский замок и парк New!!!!!!' })
  }
).then(result => result.json().then(console.log));
------------------------------------------------------------------------------------------------------------------------
// PUT save existing by city
fetch(
  '/info/city/Мир',
  {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ city: 'Мир', info: 'Мирский замок и парк New' })
  }
).then(result => result.json().then(console.log));
------------------------------------------------------------------------------------------------------------------------
// DELETE existing
fetch('/info/id/11', { method: 'DELETE' }).then(result => console.log(result))
------------------------------------------------------------------------------------------------------------------------
