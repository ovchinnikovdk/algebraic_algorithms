import numpy as np


def v_strassen(a, b, min_size):
    assert (a.shape[0] == b.shape[0] and a.shape[1] == b.shape[1] and a.shape[0] == a.shape[1])
    n = a.shape[0]
    old_size = n
    if n < min_size:
        return np.matmul(a, b)
    if n % 2 != 0:
        a = np.hstack((a, np.zeros((a.shape[0], 1))))
        a = np.vstack((a, np.zeros((1, a.shape[1]))))
        b = np.hstack((b, np.zeros((b.shape[0], 1))))
        b = np.vstack((b, np.zeros((1, b.shape[1]))))
        n += 1
    a11, a12, a21, a22 = a[:n / 2, :n / 2], a[n / 2:, :n / 2], a[:n / 2, n / 2:], a[n / 2:, n / 2:]
    b11, b12, b21, b22 = b[:n / 2, :n / 2], b[n / 2:, :n / 2], b[:n / 2, n / 2:], b[n / 2:, n / 2:]
    s1 = a21 + a22
    s2 = s1 - a11
    s3 = a11 - a21
    s4 = a12 - s2
    s5 = b12 - b11
    s6 = b22 - s5
    s7 = b22 - b12
    s8 = s6 - b21

    p1 = v_strassen(s2, s6, min_size)
    p2 = v_strassen(a11, b11, min_size)
    p3 = v_strassen(a12, b21, min_size)
    p4 = v_strassen(s3, s7, min_size)
    p5 = v_strassen(s1, s5, min_size)
    p6 = v_strassen(s4, b22, min_size)
    p7 = v_strassen(a22, s8, min_size)

    t1 = p1 + p2
    t2 = t1 + p4

    c11 = p2 + p3
    c12 = t1 + p5 + p6
    c21 = t2 - p7
    c22 = t2 + p5

    size = old_size / 2 + old_size % 2
    result = np.vstack([np.hstack([c11[:size, :size], c21[:size, :size]]),
                        np.hstack([c12[:size, :size], c22[:size, :size]])])
    return result[:old_size, :old_size]


def main():
    n = int(input())
    a = np.zeros((n, n))
    b = np.zeros((n, n))
    for i in range(n):
        numbers = raw_input().split(" ")
        for j in range(n):
            a[i][j] = int(numbers[j])

    for i in range(n):
        numbers = raw_input().split(" ")
        for j in range(n):
            b[i][j] = int(numbers[j])

    c = v_strassen(a, b, 64).astype('int')
    for i in range(n):
        print(str(c[i])[1:-1])


if __name__ == "__main__":
    main()
