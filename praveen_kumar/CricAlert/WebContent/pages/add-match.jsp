<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <link rel="stylesheet" href="/CricAlert/css/add-match.css">
    <title>Create Match</title>
</head>

<body>
    <header>
        <img class="return-icon" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="window.location='/CricAlert/pages/home.jsp'" />
        <img class="options-icon" src="/CricAlert/assets/icons/icons8-dots.png" alt="options" />
    </header>
    <section class="details">
        <form class="details__match-form" action="#">
            <div class="details__match-form__team-container">
                <div class="details__match-form__team">
                    <select name="team-a">
                        <option value="" selected disabled hidden>Choose Team</option>
                        <option value="csk">Chennai Super Kings</option>
                        <option value="srh">Sunrisers Hyderabad</option>
                        <option value="rcb">Royal Challengers Bangalore</option>
                    </select>
                    <div class="details__match-form__team-lineup">
                        Team Line Up
                    </div>
                    <div class="details__match-form__modal">
                        <div class="details__match-form__modal__players-list">
                            <span class="close">&times;</span>
                            <div class="details__match-form__modal__players-list__player"><input type="checkbox" name="a-players" value="11"><span>MS Dhoni</span></div>
                            <div class="details__match-form__modal__players-list__player"><input type="checkbox" name="a-players" value="12"><span>Suresh Raina</span></div>
                            <div class="details__match-form__modal__players-list__player"><input type="checkbox" name="a-players" value="13"><span>R Jadeja</span></div>
                        </div>            
                    </div>
                </div>
                <div class="details__match-form__team">
                    <select name="team-b">
                        <option value="" selected disabled hidden>Choose Team</option>
                        <option value="csk">Chennai Super Kings</option>
                        <option value="srh">Sunrisers Hyderabad</option>
                        <option value="rcb">Royal Challengers Bangalore</option>
                    </select>
                    <div class="details__match-form__team-lineup">
                        Team Line Up
                    </div>
                    <div class="details__match-form__modal">
                        <div class="details__match-form__modal__players-list">
                            <span class="close">&times;</span>
                            <div class="details__match-form__modal__players-list__player"><input type="checkbox" name="b-players" value="21"><span>Ricky Pointing</span></div>
                            <div class="details__match-form__modal__players-list__player"><input type="checkbox" name="b-players" value="22"><span>Dwayne Bravo</span></div>
                            <div class="details__match-form__modal__players-list__player"><input type="checkbox" name="b-players" value="23"><span>John Cena</span></div>
                        </div>            
                    </div>
                </div>
            </div>
            <input class="details__match-form__date" type="date" name="match-date" placeholder="Date"/>
        </form>
    </section>
    <footer class="confirm-details col-sm-12">
        Save Match
    </footer>
    <script src="/CricAlert/js/add-match.js"></script>
</body>

</html>