------------------------------------------------------------------------------------------------------------------------
Description of bot
------------------------------------------------------------------------------------------------------------------------
You will find "citybot" at t.me/cityInfo2020_bot.

telegram.bot.name=telegramCityInfoBot
telegram.bot.username=cityInfo2020_bot
telegram.bot.token=928935781:AAHspGUY5vBo-WMlpoLxD0W2prT03tTin10
------------------------------------------------------------------------------------------------------------------------
Description of bot's creating
------------------------------------------------------------------------------------------------------------------------
The bot is created through the BotFather (Telegram) bot:
execute the command "/ start";
execute the command "/ newbot";
------------------------------------------------------------------------------------------------------------------------
Console usability for GOOGLE CHROME:
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
