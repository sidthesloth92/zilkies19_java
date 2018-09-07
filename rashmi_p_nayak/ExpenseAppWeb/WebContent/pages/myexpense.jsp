<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../ExpenseAppWeb/css/grid.css">
    <link rel="stylesheet" href="../../ExpenseAppWeb/css/myexpense.css">
    <link rel="stylesheet" href="../../ExpenseAppWeb/fontawesome-free-5.2.0-web/css/all.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">
    <title>MyExpenses</title>
</head>

<body>
    <div class="header">
        <div class="header__app-heading col-sm-12 col-md-12 col-lg-6">
            <p>PennyWise</p>
        </div>
        <div class="header__app-tagline col-sm-12 col-md-12 col-lg-6">
            <!-- <p>Manage your expenses</p> -->
            <p>Welcome, <%= session.getAttribute("username") %></p>
        </div>
        <div class="header__navbar col-sm-12 col-md-12 col-lg-12">
            <div class="header__sidebar-outer-container col-sm-6 col-md-6 col-lg-6">
                <div class="sidebar-button-container">
                    <i class="fas fa-bars sidebar-button"></i>
                </div>
                <ul class="sidebar-options">
                    <div class="sidebar-options-container">
                        <li class="sidebar-option">
                            <a class="nav-link" href="myexpense.html">My Expenses</a>
                        </li>
                        <li class="sidebar-option">
                            <a class="nav-link" href="#">Group Expenses</a>
                        </li>
                        <li class="sidebar-option">
                            <a class="nav-link" href="#">Statistics</a>
                        </li>
                    </div>
                </ul>
            </div>
            <div class="navbar__nav-options col-sm-6 col-md-6 col-lg-6">
                <ul class="nav-options__nav-options-list">
                    <li class="nav-options-list__nav-option nav-option-active">
                        <img src="../../ExpenseAppWeb/images/rupee.png" alt="coin" class="nav-option__icon">
                        <a class="nav-link-active" href="myexpense.html">My Expenses</a>
                    </li>
                    <li class="nav-options-list__nav-option">
                        <img src="../../ExpenseAppWeb/images/group.png" alt="group" class="nav-option__icon">
                        <a class="nav-link" href="#">Group Expenses</a>
                    </li>
                    <li class="nav-options-list__nav-option">
                        <img src="../../ExpenseAppWeb/images/statistic.png" alt="statistics" class="nav-option__icon">
                        <a class="nav-link" href="#">Statistics</a>
                    </li>
                </ul>
            </div>
            <div class="navbar__search-option col-sm-6 col-md-4 col-lg-2">
                <input type="text" name="search-param" placeholder="Search.." class="search-option__input">
                <div class="search-option__icon" onclick="displaySearchInputField()">
                    <i class="fas fa-search search-btn"></i>
                </div>
            </div>
        </div>
    </div>
    <div class="expense-container">
        <div class="expense-container__expense-card-container col-sm-12 col-md-6 col-lg-4">
            <a class="nav-link" href="showexpense.html">
                <div class="expense-card-container__expense-card">
                    <img src="../../ExpenseAppWeb/images/travel.png" alt="travel" class="category-icon"></img>
                    <p class="expense-card__category-name">Travel</p>
                </div>
            </a>
            <button class="circular-btn" id="addExpenseBtn">
                <i class="fas fa-plus plus"></i>
            </button>
        </div>
        <div class="expense-container__expense-card-container col-sm-12 col-md-6 col-lg-4">
            <a class="nav-link" href="showexpense.html">
                <div class="expense-card-container__expense-card">
                    <img src="../../ExpenseAppWeb/images/education.png" alt="education" class="category-icon"></img>
                    <p class="expense-card__category-name">Education</p>
                </div>
            </a>
            <button class="circular-btn">
                <i class="fas fa-plus plus"></i>
            </button>
        </div>
        <div class="expense-container__expense-card-container col-sm-12 col-md-6 col-lg-4">
            <a class="nav-link" href="showexpense.html">
                <div class="expense-card-container__expense-card">
                    <img src="../../ExpenseAppWeb/images/utility.png" alt="utility" class="category-icon"></img>
                    <p class="expense-card__category-name">Utilities</p>
                </div>
            </a>
            <button class="circular-btn">
                <i class="fas fa-plus plus"></i>
            </button>
        </div>
        <div class="expense-container__expense-card-container col-sm-12 col-md-6 col-lg-4">
            <button class="add-btn"><i class="fas fa-plus plus"></i>Add New Category</button>
        </div>
        <div id="addExpenseModal" class="modal-container">
            <div class="modal-content">
                <span class="close">Close</span>
                <form action="#" name="new-expense-form" class="form">
                    <div class="new-expense-form__expense-type-dropdown-container col-sm-3 col-md-3 col-lg-3">
                        <div class="expense-type-dropdown-container__expense-type-icon-container">
                            <img src="../../ExpenseAppWeb/images/food.png" alt="expense-type" class="expense-type-icon-container__icon">
                        </div>
                        <select name="expense-type-dropdown-container__dropdown" id="expense-type-dropdown-container__dropdown" class="expense-type-dropdown-container__dropdown">
                            <option class="dropdown__option" value="food" onclick="changeIcon(value)">Food</option>
                            <option class="dropdown__option" value="water" onclick="changeIcon(value)">Water</option>
                            <option class="dropdown__option" value="taxi" onclick="changeIcon(value)">Taxi</option>
                            <option class="dropdown__option" value="hotel" onclick="changeIcon(value)">Hotel</option>
                            <option class="dropdown__option" value="bill" onclick="changeIcon(value)">Bill</option>
                        </select>
                    </div>
                    <div class="new-expense-form__expense-details col-sm-8 col-md-8 col-lg-8">
                        <input type="text" name="expense-description" placeholder="Enter a description.." class="input">
                        <input type="text" name="expense-amount" placeholder="Enter an amount.." class="input">
                    </div>
                    <div class="new-expense-form__submit-button col-sm-12 col-md-12 col-lg-12">
                        <input type="submit" value="Add Expense" class="submit-btn">
                    </div>
                </form>
            </div>

        </div>
    </div>
    <script src="../../ExpenseAppWeb/js/myexpense.js"></script>
</body>

</html>