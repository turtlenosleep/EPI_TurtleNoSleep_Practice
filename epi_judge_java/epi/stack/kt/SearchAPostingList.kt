package epi.stack.kt

import epi.PostingListNode
import java.util.*

class SearchAPostingList {

    fun setJumpOrder(list: PostingListNode) {
        val stack = LinkedList<PostingListNode>();
        stack.push(list);

        var order = 0;
        while (!stack.isEmpty()) {
            val node = stack.pop();
            if (node != null && node.order == -1) {
                node.order = order++;

                stack.push(node.next);
                stack.push(node.jump);
            }
        }
    }
}