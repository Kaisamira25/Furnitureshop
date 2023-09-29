function getCartFromLS() {
    return localStorage.getItem("cart") ? JSON.parse(localStorage.getItem("cart")) : []
}

function setCartToLS(cart) {
    localStorage.setItem("cart", JSON.stringify(cart))
}

function getTotalItem() {
    const cart = getCartFromLS();
    return cart.reduce((previousValue, currentValue) => {
        return previousValue + currentValue.quantity
    }, 0)
}

app.controller("CartController", function ($scope, $http, $location) {
    $scope.isLogin = !!localStorage.getItem("token") && localStorage.getItem("token") != "undefined"
    $scope.cart = getCartFromLS();
    $scope.address = ""
    $scope.total = getTotalItem();
    $scope.addToCart = function (product) {
        const exists = $scope.cart.find((item) => item.product.id === product.id)
        if (exists) {
            exists.quantity++;
        } else {
            $scope.cart.push({product, quantity: 1})
        }
        setCartToLS($scope.cart)
        $scope.total = getTotalItem();
    }
    $scope.decreaseQuantity = (product) => {
        let exists = $scope.cart.find(item => item.product.id === product.id)
        if (exists && exists.quantity > 0) {
            exists.quantity--;
            $scope.total--
        }
    }
    $scope.increaseQuantity = (product) => {
        let exists = $scope.cart.find(item => item.product.id === product.id)
        if (exists) {
            exists.quantity++;
            $scope.total++
        }
    }

    $scope.getTotalPrice = () => {
        return $scope.cart.reduce((prev, acc) => {
            return prev + acc.product.price * acc.quantity
        }, 0)
    }

    $scope.removeProduct = (product) => {
        $scope.cart = $scope.cart.filter(item => item.product.id !== product.id)
        setCartToLS($scope.cart)
    }
    $scope.checkOut = () => {
        if ($scope.address === "") {
            alert("Vui long nhap dia chi")
            return;
        } else {
            $http.post("http://localhost:8080/api/checkout", {
                address: $scope.address,
                total: $scope.getTotalPrice(),
                orderDetailList: $scope.cart
            }, {
                headers: {
                    "Authorization": localStorage.getItem("token") // Add the token to the Authorization header
                }
            })
                .then(function (response) {
                    alert("Success")
                    $scope.cart = []
                    setCartToLS([])
                })
                .catch(function (error) {
                    if (error.status === 401 || error.status === 403) {
                        window.location.href = "/login";
                    }
                });

        }
    }
})