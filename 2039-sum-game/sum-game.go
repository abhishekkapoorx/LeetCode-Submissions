func sumGame(num string) bool {
	n := len(num)

	diff := 0
	q1, q2 := 0, 0

	for i := 0; i < n/2; i++ {
		if num[i] == '?' {
			q1++
		} else {
			diff += int(num[i] - '0')
		}
	}

	for i := n / 2; i < n; i++ {
		if num[i] == '?' {
			q2++
		} else {
			diff -= int(num[i] - '0')
		}
	}

	// Same number of '?' on both sides.
	// Bob can mirror Alice's moves.
	if q1 == q2 {
		return diff != 0
	}

	// Left has more '?'.
	if q1 > q2 {
		extra := q1 - q2

		// Odd difference -> Alice can force inequality.
		if extra%2 == 1 {
			return true
		}

		// Bob wins only when the fixed difference can
		// exactly be compensated.
		return diff != -9*(extra/2)
	}

	// Right has more '?'.
	extra := q2 - q1

	if extra%2 == 1 {
		return true
	}

	return diff != 9*(extra/2)
}