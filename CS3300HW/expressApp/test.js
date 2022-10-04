const axios = require("axios");

axios.get("http://localhost:3000/api/products").then((res) => {
  console.log(res.data);
});

axios.get("http://localhost:3000/api/products/12").then((res) => {
  console.log(res.data);
});

axios
  .post("http://localhost:3000/api/products", {
    id: "64",
    name: "testname",
    quantity: "123",
  })
  .then((res) => {
    console.log(res.data);
  });

axios
  .put("http://localhost:3000/api/products/64", {
    id: "64",
    name: "updatedname",
    quantity: "124",
  })
  .then((res) => {
    console.log(res.data);
  });

axios.delete("http://localhost:3000/api/products/64").then((res) => {
  console.log(res.data);
});
