app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.form = {};
    $scope.initialize = function () {
        // load product
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            })
        })
        // load category
        $http.get("/rest/categories").then(resp => {
            $scope.category = resp.data
        })
    }
    $scope.initialize();

    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,

        }

    }
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        var myTab = new bootstrap.Tab(document.querySelector(".nav-tabs a:first-child"));
        myTab.show();
    }
    $scope.create = function () {
        var item = angular.copy($scope.form)
        $http.post('/rest/products', item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data)
            $scope.reset()
            $scope.initialize();
            alert("Thêm thành công")
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm")
            console.log("Error", error)
        })
    }
    $scope.update = function () {
        var item = angular.copy($scope.form)
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id)
            $scope.items[index] = item
            $scope.initialize();
            alert("Cập nhật thành công")
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm")
            console.log("Error", error)
        })
    }
    $scope.delete = function (item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id)
            $scope.items.splice(index, 1)
            $scope.reset()
            $scope.initialize();
            alert("Xóa thành công")
        }).catch(error => {
            alert("Lỗi xóa sản phẩm")
            console.log("Error", error)
        })
    }
    $scope.imageChanged = function (files) {
        var data = new FormData()
        data.append('file', files[0])
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi load hình ảnh")
            console.log("Error", error)
        })
    }

    // Số sản phẩm trên mỗi trang
    $scope.itemsPerPage = 10;
    // Trang hiện tại
    $scope.currentPage = 1;

    // Hàm chuyển trang
    $scope.setPage = function(pageNumber) {
        $scope.currentPage = pageNumber;
    };

    // Hàm lấy sản phẩm của trang hiện tại
    $scope.getCurrentPageProducts = function() {
        var startIndex = ($scope.currentPage - 1) * $scope.itemsPerPage;
        var endIndex = startIndex + $scope.itemsPerPage;
        return $scope.items.slice(startIndex, endIndex);
    };

    // Hàm tính tổng số trang
    $scope.getTotalPages = function() {
        return Math.ceil($scope.items.length / $scope.itemsPerPage);
    };

    // Hàm kiểm tra trang đầu tiên
    $scope.isFirstPage = function() {
        return $scope.currentPage === 1;
    };

    // Hàm kiểm tra trang cuối cùng
    $scope.isLastPage = function() {
        return $scope.currentPage === $scope.getTotalPages();
    };
    // Hàm tạo một mảng các số trang để hiển thị trong phân trang
    $scope.getPagesArray = function() {
        var pagesArray = [];
        var totalPages = $scope.getTotalPages();
        var currentPage = $scope.currentPage;
        var visiblePages = 5; // Số lượng trang hiển thị trước và sau trang hiện tại

        // Logic để chỉ hiển thị một số trang xung quanh trang hiện tại
        var startPage = Math.max(currentPage - visiblePages, 1);
        var endPage = Math.min(currentPage + visiblePages, totalPages);

        // Thêm liên kết trang vào mảng
        for (var i = startPage; i <= endPage; i++) {
            pagesArray.push(i);
        }

        // Thêm liên kết "..." ở đầu và cuối nếu cần
        if (startPage > 1) {
            pagesArray.unshift('...');
        }

        if (endPage < totalPages) {
            pagesArray.push('...');
        }

        return pagesArray;
    };


})

