// Authorization token that must have been created previously. See : https://developer.spotify.com/documentation/web-api/concepts/authorization
const token = 'BQD8KPG63Z_DOZ9LUTQWqrvDhaTdvSefTzQ-0tqdVra6F1WR3ZhKpibl39_fP1db2pd9yvXvICB1IdhIPg8Juo4ve1OVS5h0IuzIcNPhCIUXvcUvlwrRMmB63lMIZ9NpevLvaqGE68uF5WuMh2LvW0Vx1LRanswsHs60YkDh4SYBW00IfJz_2xQJsA5zvWw58YOYWuQeF88OJnweLYJMV6gQ6F7YI8D2PTZRDWxdK_MKYTmdkKJtIvqA6hgBDYC04mJjcQueQLScu_pZfbU9KSFGXGSMuVnicXyscwAyJ3bO4HIikScdNZVUaxu9FDG7HK40iDxC';
async function fetchWebApi(endpoint, method, body) {
  const res = await fetch(`https://api.spotify.com/${endpoint}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    method,
    body:JSON.stringify(body)
  });
  return await res.json();
}

async function getTopTracks(){
  // Endpoint reference : https://developer.spotify.com/documentation/web-api/reference/get-users-top-artists-and-tracks
  return (await fetchWebApi(
    'v1/me/top/tracks?time_range=long_term&limit=5', 'GET'
  )).items;
}

const topTracks = await getTopTracks();
console.log(
  topTracks?.map(
    ({name, artists}) =>
      `${name} by ${artists.map(artist => artist.name).join(', ')}`
  )
);
