package depth_first_search;

import java.util.*;
import java.util.stream.Stream;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first
 * element accounts[i][0] is a name, and the rest of the elements are emails representing emails
 * of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person
 * if there is some common email to both accounts. Note that even if two accounts have the same
 * name, they may belong to different people as people could have the same name. A person can have
 * any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of
 * each account is the name, and the rest of the elements are emails in sorted order. The accounts
 * themselves can be returned in any order.
 *
 * Example 1:
 *      Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
 *                         ["John","johnsmith@mail.com","john00@mail.com"],
 *                         ["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 *      Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 *               ["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 *      Explanation: The first and second John's are the same person as they have the common email
 *                   "johnsmith@mail.com". The third John and Mary are different people as none of
 *                   their email addresses are used by other accounts.
 *                  We could return these lists in any order, for example the answer
 *                  [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 *                  ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']]
 *                  would still be accepted.
 *
 * Example 2:
 *      Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
 *                         ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
 *                         ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
 *                         ["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
 *                         ["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 *      Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],
 *               ["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
 *               ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],
 *               ["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
 *               ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 * Constraints:
 *      - 1 <= accounts.length <= 1000
 *      - 2 <= accounts[i].length <= 10
 *      - 1 <= accounts[i][j].length <= 30
 *      - accounts[i][0] consists of English letters.
 *      - accounts[i][j] (for j > 0) is a valid email.
 */
public class AccountsMerge {

    public static void main(String[] args) throws Exception {
        /*
        new AccountsMerge().accountsMerge(Arrays.asList(
                Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
                Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
                Arrays.asList("Mary","mary@mail.com"),
                Arrays.asList("John","johnnybravo@mail.com")
        )).stream().forEach(System.out::println);
         */
        new AccountsMerge().accountsMerge(Arrays.asList(
                Arrays.asList("David","David0@m.co","David1@m.co"),
                Arrays.asList("David","David3@m.co","David4@m.co"),
                Arrays.asList("David","David4@m.co","David5@m.co"),
                Arrays.asList("David","David2@m.co","David3@m.co"),
                Arrays.asList("David","David1@m.co","David2@m.co")
        )).stream().forEach(System.out::println);
    }

    class DisjointSet {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        int findUParent(int node) {
            if (node == parent.get(node)) return node;
            int ulp = findUParent(parent.get(node));
            parent.set(node, ulp);
            return parent.get(node);
        }

        void unionBySize(int u, int v) {
            int ulpU = findUParent(u), ulpV = findUParent(v);
            if (ulpU == ulpV) return;
            if (size.get(ulpU) < size.get(ulpV)) {
                parent.set(ulpU, ulpV);
                size.set(ulpV, size.get(ulpU) + size.get(ulpV));
            } else {
                parent.set(ulpV, ulpU);
                size.set(ulpU, size.get(ulpU) + size.get(ulpV));
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int len = accounts.size();
        DisjointSet ds = new DisjointSet(len);
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int ilen = accounts.get(i).size();
            for (int j = 1; j < ilen; j++) {
                String tmp = accounts.get(i).get(j);
                if (!map.containsKey(tmp)) map.put(tmp, i);
                else ds.unionBySize(i, map.get(tmp));
            }
        }
        ArrayList<String>[] tmp = new ArrayList[len];
        for (int i = 0; i < len; i++) tmp[i] = new ArrayList<String>();
        for (Map.Entry<String, Integer> it : map.entrySet()) {
            String mail = it.getKey();
            int node = ds.findUParent(it.getValue());
            tmp[node].add(mail);
        }
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (tmp[i].size() == 0) continue;
            ArrayList<String> txp = new ArrayList<>();
            txp.add(accounts.get(i).get(0));
            Collections.sort(tmp[i]);
            for (String s : tmp[i]) txp.add(s);
            res.add(txp);
        }
        return res;
    }

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        // emailMap<String, AccountIdx>
        // accountNode<Integer, sames>

        List<Set<Integer>> accountMap = new ArrayList<>();
        for(int idx=0; idx<accounts.size(); idx++) {
            accountMap.add(new HashSet<>());
        }
        Map<String, Integer> emailMap = new HashMap<>();
        for(int acctIdx=0; acctIdx < accounts.size(); acctIdx++) {
            var account = accounts.get(acctIdx);
            for(int idx=1; idx<account.size(); idx++) {
                var email = account.get(idx);
                if (emailMap.containsKey(email)) {
                    var otherAcctIdx = emailMap.get(email);
                    accountMap.get(otherAcctIdx).add(acctIdx);
                    accountMap.get(acctIdx).add(otherAcctIdx);
                    // found
                } else {
                    emailMap.put(account.get(idx), acctIdx);
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        List<List<String>> ret = new ArrayList<>();

        for(int acctIdx=0; acctIdx < accountMap.size(); acctIdx++) {
            if (visited.contains(acctIdx)) {
                continue;
            }
            SortedSet<String> emails = new TreeSet<>();
            Queue<Integer> accountQueue = new ArrayDeque<>();
            accountQueue.add(acctIdx);
            while (! accountQueue.isEmpty()) {
                var curIdx = accountQueue.poll();
                var account = accounts.get(curIdx);
                visited.add(curIdx);
                for (int idx = 1; idx < account.size(); idx++) {
                    emails.add(account.get(idx));
                }
                for (var cur: accountMap.get(curIdx)) {
                    if (! visited.contains(cur)) {
                        accountQueue.add(cur);
                    }
                }
            }
            if (! emails.isEmpty()) {
                List<String> newAccount = new ArrayList<>();
                newAccount.add(accounts.get(acctIdx).get(0));
                newAccount.addAll(emails);
                ret.add(newAccount);
            }
        }
        return ret;
    }
}
