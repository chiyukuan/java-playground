package my;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GitHubApiTest {

    public final static class Commit{
        final String id;
        final long timestamp;
        final String author;
        public Commit(String id,long timestamp,String author){
            this.id = id;
            this.timestamp = timestamp;
            this.author = author;
        }
    }

    public  interface Repo {
        List<Commit> getCommits();
    }

    public static class RepoMark implements Repo {

        public List<Commit> commits;

        public RepoMark(List<Commit> commits) {
            this.commits = commits;
        }
        @Override
        public List<Commit> getCommits() {
            return this.commits;
        }
    }

    public  interface GitHubApi {
        List<Repo> getRepos();
    }

    public static class GitHubApiImpl implements GitHubApi {

        public List<Repo> repos;

        public GitHubApiImpl(List<Repo> repos) {
            this.repos = repos;
        }
        @Override
        public List<Repo> getRepos() {
            return this.repos;
        }
    }

    public static void main(String args[]) {
        List<Repo> repos = new ArrayList<>();
        List<Commit> commits = new ArrayList<>();
        Repo repo = new RepoMark(commits);
        repos.add(repo);
        commits.add(new Commit("a01", 1020, "A-1"));
        commits.add(new Commit("a01", 1010, "A-2"));
        commits.add(new Commit("a01", 1000, "A-3"));

        commits = new ArrayList<>();
        repo = new RepoMark(commits);
        repos.add(repo);
        commits.add(new Commit("a01", 1030, "A-2"));
        commits.add(new Commit("a01", 1050, "A-3"));
        commits.add(new Commit("a01", 1015, "A-1"));

        commits = new ArrayList<>();
        repo = new RepoMark(commits);
        repos.add(repo);
        commits.add(new Commit("a01", 1027, "A-2"));
        commits.add(new Commit("a01", 1023, "A-2"));
        commits.add(new Commit("a01", 1012, "A-2"));


        GitHubApi api = new GitHubApiImpl(repos);
        // Stream<Commit> lastCommits = null;

        api.getRepos().stream().flatMap(r -> r.getCommits().stream())
                .sorted((Commit o1, Commit o2) -> Long.compare(o2.timestamp, o1.timestamp))
                .limit(5).forEach(commit ->
                    System.out.printf("forEach %d %s\n", commit.timestamp, commit.author)
                );
        // parallel will cause the elm pulling order undefined.
        // Need to materialize (toList ) it to confirm the pulling order
        api.getRepos().stream().flatMap(r -> r.getCommits().stream()).parallel()
                .sorted((Commit o1, Commit o2) -> Long.compare(o2.timestamp, o1.timestamp))
                .limit(5).toList().forEach(commit ->
                        System.out.printf("toList %d %s\n", commit.timestamp, commit.author)
                );

        api.getRepos().stream().flatMap(r -> r.getCommits().stream()).parallel()
                .filter(commit -> commit.timestamp % 2 == 0)
                .sorted((Commit o1, Commit o2) -> Long.compare(o2.timestamp, o1.timestamp))
                .limit(5).toList().forEach(commit ->
                        System.out.printf("filter %d %s\n", commit.timestamp, commit.author)
                );
    }
}
