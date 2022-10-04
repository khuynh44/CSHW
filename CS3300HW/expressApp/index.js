const express = require("express");
const fs = require("fs");
const { parse } = require("csv-parse");
const { stringify } = require("csv-stringify");
const { get } = require("express/lib/response");
const app = express();

app.use(express.urlencoded({extended:true}))
app.use(express.json())

const db = new Map();

function updateDB() {
    const stringifier = stringify({ header: true, columns: ["id", "name", "quantity"] });
    const writableStream = fs.createWriteStream("./electronic_items-1.csv");
    db.forEach((value) => {
        stringifier.write(Object.values(value))
    })
    stringifier.pipe(writableStream);
    console.log(db)
}

fs.createReadStream("./electronic_items-1.csv")
  .pipe(parse({ delimiter: ",", from_line: 2 }))
  .on("data", function (row) {
    db.set(row[0], { id: row[0], name: row[1], quantity: row[2] });
  });

app.get("/api/products", (req, res) => {
  res.send(Array.from(db.values()));
});

app.get("/api/products/:id", (req, res) => {
  let obj = db.get(req.params.id.toString());
  let send = "product does not exist";
  if (obj != undefined) {
    send = obj
  }
  res.send(send);
});

app.post("/api/products", (req, res) => {
    let obj = req.body;
    if (db.get(obj.id) != undefined) {
        res.send(`product with ${obj.id} already exists`)
        return
    }
    db.set(obj.id, obj)
    updateDB()
    res.send("Successful!")
})

app.put("/api/products/:id", (req, res) => {
    db.set(req.params.id.toString(), req.body)
    updateDB()
    res.send("Successful!")
})

app.delete("/api/products/:id", (req, res) => {
    db.delete(req.params.id.toString())
    updateDB()
    res.send("Successful!")
})

app.listen(3000, () => {
  console.log(`Server is running on 3000`);
});
