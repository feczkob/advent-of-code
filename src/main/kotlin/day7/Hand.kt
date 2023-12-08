package day7

class Hand(
    private val cards: Array<CardValue>,
    val bid: Int,
    private val isPart2: Boolean = false,
) : Comparable<Hand> {

    private val type: Int
        get() = when {
            isFiveOfAKind() -> 6
            isFourOfAKind() -> 5
            isFullHouse() -> 4
            isThreeOfAKind() -> 3
            isTwoPair() -> 2
            isOnePair() -> 1
            else -> 0
        }

    override fun compareTo(other: Hand) =
        when {
            type > other.type -> 1
            type < other.type -> -1
            else -> compareCardByCard(other)
        }

    private fun isFiveOfAKind() =
        cards.groupBy { it }.values.any { it.size == 5 } ||
                (isPart2 && isFiveOfAKindPart2())

    private fun isFiveOfAKindPart2() =
        (cards.groupBy { it }.values.any { it.size == 4 } && cards.count { it == CardValue.J } == 1) ||
                (cards.groupBy { it }.values.any { it.size == 3 } && cards.count { it == CardValue.J } == 2) ||
                (cards.groupBy { it }.values.any { it.size == 2 } && cards.count { it == CardValue.J } == 3) ||
                (cards.groupBy { it }.values.any { it.size == 1 } && cards.count { it == CardValue.J } == 4)

    private fun isFourOfAKind() =
        cards.groupBy { it }.values.any { it.size == 4 } ||
                (isPart2 && isFourOfAKindPart2())

    private fun isFourOfAKindPart2() =
        (cards.groupBy { it }.values.any { it.size == 3 } && cards.count { it == CardValue.J } == 1) ||
                (isTwoPair() && cards.count { it == CardValue.J } == 2) ||
                (cards.distinctBy { it }.size == 3 && cards.count { it == CardValue.J } == 3)

    private fun isFullHouse() =
        cards.groupBy { it }.values.any { it.size == 3 } &&
                cards.groupBy { it }.values.any { it.size == 2 } ||
                (isPart2 && isFullHousePart2())

    private fun isFullHousePart2() =
        isTwoPair() && cards.count { it == CardValue.J } == 1

    private fun isThreeOfAKind() =
        cards.groupBy { it }.values.any { it.size == 3 } || (isPart2 && isThreeOfAKindPart2())

    private fun isThreeOfAKindPart2() =
        (cards.groupBy { it }.values.any { it.size == 2 } && cards.count { it == CardValue.J } == 1) ||
                (cards.distinctBy { it }.size == 4 && cards.count { it == CardValue.J } == 2)

    private fun isTwoPair() =
        cards.groupBy { it }.values.filter { it.size == 2 }.size == 2

    private fun isOnePair() =
        cards.groupBy { it }.values.any { it.size == 2 }
                || (isPart2 && isOnePairPart2())

    private fun isOnePairPart2() =
        (cards.distinctBy { it }.size == 5 && cards.count { it == CardValue.J } == 1)

    // If none from the above, then it's of type High card

    private fun compareCardByCard(other: Hand): Int {
        var index = 0
        while (cards[index] == other.cards[index]) {
            index++
        }
        return if(!isPart2) {
            cards[index].part1.compareTo(other.cards[index].part1)
        } else {
            cards[index].part2.compareTo(other.cards[index].part2)
        }
    }

    override fun toString() =
        cards.joinToString("") { it.toString() } + " $bid" + " rank: $type"

}