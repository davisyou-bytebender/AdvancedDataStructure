package DataStructureAboutString;

import java.util.HashMap;

public class TrieTree {
    //字典树节点
    class TrieNode {
        public int path;  // path表示有多少个单词共用这个节点，如此我们才能知道某字符串pre为前缀的单词数量
        public int end;   // end表示有多少个单词以这个节点结尾，只要end大于0，那么说明存在单词以该节点结尾，亦表示该节点为终止节点
        public HashMap<Character, TrieNode> map;

        public TrieNode() {
            path = 0;
            end = 0;
            map = new HashMap<>();
        }
    }

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null)
            return;
        TrieNode node = root;
        node.path++;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (node.map.get(words[i]) == null) {
                node.map.put(words[i], new TrieNode());
            }
            node = node.map.get(words[i]);
            node.path++;
        }
        node.end++;
    }

    public boolean search(String word) {
        if (word == null)
            return false;
        TrieNode node = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (node.map.get(words[i]) == null)
                return false;
            node = node.map.get(words[i]);
        }
        return node.end > 0;
    }

    public void delete(String word) {
        if (search(word)) {
            char[] words = word.toCharArray();
            TrieNode node = root;
            node.path--;
            for (int i = 0; i < words.length; i++) {
                if (--node.map.get(words[i]).path == 0) {
                    node.map.remove(words[i]);
                    return;
                }
                node = node.map.get(words[i]);
            }//for
            node.end--;
        }//if
    }

    public int prefixNumber(String pre) {
        if (pre == null)
            return 0;
        TrieNode node = root;
        char[] pres = pre.toCharArray();
        for (int i = 0; i < pres.length; i++) {
            if (node.map.get(pres[i]) == null)
                return 0;
            node = node.map.get(pres[i]);
        }
        return node.path;
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        System.out.println(trie.search("小游的数据挖掘机"));//f
        trie.insert("小游的数据挖掘机");
        System.out.println(trie.search("小游的数据挖掘机"));//t
        trie.delete("小游的数据挖掘机");
        System.out.println(trie.search("小游的数据挖掘机"));//f
        trie.insert("小游的数据挖掘机");
        trie.insert("小游的数据挖掘机");
        trie.delete("小游的数据挖掘机");
        System.out.println(trie.search("小游的数据挖掘机"));//t
        trie.delete("小游的数据挖掘机");
        System.out.println(trie.search("小游的数据挖掘机"));//f
        trie.insert("小游的数据挖掘机一");
        trie.insert("小游的数据挖掘机二");
        trie.insert("小游的数据挖掘机一三");
        trie.insert("小游的数据挖掘机一四");
        trie.delete("小游的数据挖掘机一");
        System.out.println(trie.search("小游的数据挖掘机一"));//f
        System.out.println(trie.prefixNumber("小游的数据挖掘机"));//3

    }
}

