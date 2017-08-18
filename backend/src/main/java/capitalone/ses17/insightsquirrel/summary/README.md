# Calls

.pastSpending(dateRange, location, category)

  - total of all transactions in location in category in the dateRange
  - `dateRange`: defaults to past week
  - `location`: defaults to all
  - `category`: defaults to all
  - elastic: get me the total of all hits

.futureSpending(dateRange, category)

  - predicts future total spending in category in the dateRange
  - `dateRange`: defaults to next week
  - `category`: defaults to all
  - elastic: get me the average of all hits

.advice()

  - looks at categories and which are being spent in the most
  - elastic: get me the profile (% of spending in each category) of all hits
