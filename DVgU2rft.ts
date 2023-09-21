/*
  You're given a two-dimensional array (a matrix) of potentially unequal height
  and width containing only 0s and 1s
  
  Each 0 represents land, each 1 represents part of a river.
  A river conssits of any number of 1s that are either horizontally or vertically adjacent(but not diagonally adjacent).
  The number of adjacent 1s forming a river determines it's size.

  A river can twist. It doesn't have to be straight vertical line or straight horizontal line.
  It can be L-shaped, for example.

  Write a function that returns an array of the sizes of all rivers represented in the input matrix.
  The sizes don't need to be in any particular order

  Sample input:
            matrix = [
                [1, 0, 0, 1, 0],
                [1, 0, 1, 0, 0],
                [0, 0, 1, 0, 1],
                [1, 0, 1, 0, 1],
                [1, 0, 1, 1, 0],
               ]
*/

export function riverSizes(matrix: number[][]) {
    const visited: boolean[][] = Array(matrix.length).fill(false).map(() => Array(matrix[0].length).fill(false))

    const resp = [];
    for (let row = 0; row < matrix.length; row++) {
        for (let col = 0; col < matrix[0].length; col++) { 
            if (visited[row][col] || matrix[row][col] === 0) continue;

            const depth = calculateDepth(matrix, visited, row, col);
            resp.push(depth);
        }
    }
    return resp;
}

function calculateDepth(matrix: number[][], visited: boolean[][], row: number, col: number): number {
    if ((row < 0 || col < 0) || (row > matrix.length - 1 || col > matrix[0].length)) return 0;
    if (visited[row][col]) return 0;

    let depth = 0;

    if (matrix[row][col] === 1) {
        visited[row][col] = true;
        depth += 1;

        const down = calculateDepth(matrix, visited, row + 1, col)
        const left = calculateDepth(matrix, visited, row, col - 1)
        const right = calculateDepth(matrix, visited, row, col + 1)
        const up = calculateDepth(matrix, visited, row-1, col)

        depth = depth + down + left + right + up;
    } else { visited[row][col] = true };
    return depth;
}